package com.nhnacademy.hello.order;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}