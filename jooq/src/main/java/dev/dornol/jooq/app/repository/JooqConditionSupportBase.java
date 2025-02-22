package dev.dornol.jooq.app.repository;

import jakarta.annotation.Nonnull;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.springframework.util.StringUtils;

import java.util.Collection;

public interface JooqConditionSupportBase {

    default Condition contains(@Nonnull Collection<TableField<? extends Record, String>> fields, String text) {
        var condition = DSL.noCondition();

        if (!fields.isEmpty() && StringUtils.hasText(text)) {
            for (var field : fields) {
                condition = condition.or(field.eq(text));
            }
        }

        return condition;
    }

}
