package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.FarmStatus;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QFarmStatus is a Querydsl query type for FarmStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFarmStatus extends EntityPathBase<FarmStatus> {

    private static final long serialVersionUID = -944946380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFarmStatus farmStatus = new QFarmStatus("farmStatus");

    public final QFarm farm;

    public final StringPath farmStatusId = createString("farmStatusId");

    public final DateTimePath<java.sql.Timestamp> lastUpdatedTime = createDateTime("lastUpdatedTime", java.sql.Timestamp.class);

    public final StringPath message = createString("message");

    public final StringPath status = createString("status");

    public QFarmStatus(String variable) {
        this(FarmStatus.class, forVariable(variable), INITS);
    }

    public QFarmStatus(Path<? extends FarmStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFarmStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFarmStatus(PathMetadata metadata, PathInits inits) {
        this(FarmStatus.class, metadata, inits);
    }

    public QFarmStatus(Class<? extends FarmStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.farm = inits.isInitialized("farm") ? new QFarm(forProperty("farm"), inits.get("farm")) : null;
    }

}

