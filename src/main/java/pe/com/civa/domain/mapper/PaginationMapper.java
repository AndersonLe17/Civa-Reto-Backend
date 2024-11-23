package pe.com.civa.domain.mapper;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.data.domain.Page;
import pe.com.civa.config.response.PaginationResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class PaginationMapper {

    public static <T> PaginationResponse paginationResponseMapper(T data, Page page, URI uri) throws URISyntaxException {
        return PaginationResponse.builder()
                .data(data)
                .totalDocs((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .totalElements((int) page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .page(page.getNumber())
                .size(page.getSize())
                .sort(page.getSort().stream().map(s -> s.getProperty() + "," + s.getDirection()).reduce((s1, s2) -> s1 + "," + s2).orElse(null))
                .prevPage(page.hasPrevious() ? page.getNumber() - 1 : null)
                .nextPage(page.hasNext() ? page.getNumber() + 1 : null)
                .hasPrevPage(page.hasPrevious())
                .hasNextPage(page.hasNext())
                .prevLink(page.hasPrevious() ? new URIBuilder(uri).setParameter("page", String.valueOf(page.getNumber() - 1)).build() : null)
                .nextLink(page.hasNext() ? new URIBuilder(uri).setParameter("page", String.valueOf(page.getNumber() + 1)).build() : null)
                .build();
    }

}
