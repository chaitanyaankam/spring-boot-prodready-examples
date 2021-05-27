package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.Land;
import com.farm.domain.entity.LandOwner;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QLandOwner is a Querydsl query type for LandOwner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLandOwner extends EntityPathBase<LandOwner> {

    private static final long serialVersionUID = -1801576804L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLandOwner landOwner = new QLandOwner("landOwner");

    public final StringPath alt_mobile_number = createString("alt_mobile_number");

    public final StringPath email = createString("email");

    public final StringPath idType = createString("idType");

    public final SetPath<Land, QLand> lands = this.<Land, QLand>createSet("lands", Land.class, QLand.class, PathInits.DIRECT2);

    public final StringPath mobile_number = createString("mobile_number");

    public final QName name;

    public final StringPath national_identifer = createString("national_identifer");

    public QLandOwner(String variable) {
        this(LandOwner.class, forVariable(variable), INITS);
    }

    public QLandOwner(Path<? extends LandOwner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLandOwner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLandOwner(PathMetadata metadata, PathInits inits) {
        this(LandOwner.class, metadata, inits);
    }

    public QLandOwner(Class<? extends LandOwner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.name = inits.isInitialized("name") ? new QName(forProperty("name")) : null;
    }

}

