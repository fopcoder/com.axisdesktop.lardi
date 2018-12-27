package com.axisdesktop.lardi.helper;

import java.util.List;
import org.springframework.data.domain.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResultSet<T> {
  private int limit;
  private int offset;
  private long totalElements;
  private int totalPages;
  private List<T> rows;

  public PageResultSet(Page<T> page, int offset, int limit) {
    this.limit = limit;
    this.offset = offset;
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.rows = page.getContent();
  }

}
