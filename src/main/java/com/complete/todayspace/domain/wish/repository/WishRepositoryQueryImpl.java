package com.complete.todayspace.domain.wish.repository;

import com.complete.todayspace.domain.product.entity.Product;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import static com.complete.todayspace.domain.wish.entity.QWish.wish;

@RequiredArgsConstructor
public class WishRepositoryQueryImpl implements WishRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Product> findTopWishedProducts(Pageable pageable) {
        // Define the date one week ago
        LocalDateTime oneWeekAgo = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);

        // Query to get the products with the most wishes in the last week
        var query = query(wish.product, oneWeekAgo)
            .orderBy(Expressions.numberTemplate(Long.class, "count({0})", wish.id).desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());

        // Fetch the results
        var products = query.fetch();

        // Query to get the total count of products
        var countQuery = query(wish.product.count(), oneWeekAgo);
        long totalSize = countQuery.fetch().get(0);

        // Return the page
        return PageableExecutionUtils.getPage(products, pageable, () -> totalSize);
    }

    private <T> JPAQuery<T> query(Expression<T> expr, LocalDateTime oneWeekAgo) {
        return jpaQueryFactory.select(expr)
            .from(wish)
            .join(wish.product)
            .where(
                wishCreatedAfter(oneWeekAgo)
            );
    }

    private BooleanExpression wishCreatedAfter(LocalDateTime oneWeekAgo) {
        return wish.createdAt.after(oneWeekAgo);
    }
}