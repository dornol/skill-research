package dev.dornol.jooq.app.repository;

import dev.dornol.jooq.target.Tables;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BidRepository {
    private final DSLContext dsl;

    public void test() {
        Record result = dsl
                .select()
                .from(Tables.BID)
                .innerJoin(Tables.BID.bidItem())
                .where(Tables.BID.ID.eq(1))
                .fetchOne();
    }

}
