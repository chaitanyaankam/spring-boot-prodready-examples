package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.Farm;
import com.farm.domain.entity.FarmStatus;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.MapPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QFarm is a Querydsl query type for Farm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFarm extends EntityPathBase<Farm> {

    private static final long serialVersionUID = -416347998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFarm farm = new QFarm("farm");

    public final StringPath farmId = createString("farmId");

    public final QFarmOwner farmOwner;

    public final MapPath<String, String, StringPath> images = this.<String, String, StringPath>createMap("images", String.class, String.class, StringPath.class);

    public final QLand land;

    public final QLandSize size;

    public final SetPath<FarmStatus, QFarmStatus> status = this.<FarmStatus, QFarmStatus>createSet("status", FarmStatus.class, QFarmStatus.class, PathInits.DIRECT2);

    public QFarm(String variable) {
        this(Farm.class, forVariable(variable), INITS);
    }

    public QFarm(Path<? extends Farm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFarm(PathMetadata metadata, PathInits inits) {
        this(Farm.class, metadata, inits);
    }

    public QFarm(Class<? extends Farm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.farmOwner = inits.isInitialized("farmOwner") ? new QFarmOwner(forProperty("farmOwner"), inits.get("farmOwner")) : null;
        this.land = inits.isInitialized("land") ? new QLand(forProperty("land"), inits.get("land")) : null;
        this.size = inits.isInitialized("size") ? new QLandSize(forProperty("size")) : null;
    }

}

