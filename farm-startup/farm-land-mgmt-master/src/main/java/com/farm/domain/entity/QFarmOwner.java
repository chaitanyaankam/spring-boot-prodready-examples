package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.Farm;
import com.farm.domain.entity.FarmOwner;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QFarmOwner is a Querydsl query type for FarmOwner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFarmOwner extends EntityPathBase<FarmOwner> {

    private static final long serialVersionUID = -34074831L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFarmOwner farmOwner = new QFarmOwner("farmOwner");

    public final QAddress address;

    public final ListPath<Farm, QFarm> farms = this.<Farm, QFarm>createList("farms", Farm.class, QFarm.class, PathInits.DIRECT2);

    public final StringPath plotOwnerId = createString("plotOwnerId");

    public QFarmOwner(String variable) {
        this(FarmOwner.class, forVariable(variable), INITS);
    }

    public QFarmOwner(Path<? extends FarmOwner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFarmOwner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFarmOwner(PathMetadata metadata, PathInits inits) {
        this(FarmOwner.class, metadata, inits);
    }

    public QFarmOwner(Class<? extends FarmOwner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
    }

}

