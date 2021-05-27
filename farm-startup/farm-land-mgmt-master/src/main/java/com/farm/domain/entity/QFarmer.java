package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.Farmer;
import com.farm.domain.entity.Land;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QFarmer is a Querydsl query type for Farmer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFarmer extends EntityPathBase<Farmer> {

    private static final long serialVersionUID = -678464305L;

    public static final QFarmer farmer = new QFarmer("farmer");

    public final StringPath farmerId = createString("farmerId");

    public final StringPath image = createString("image");

    public final SetPath<Land, QLand> lands = this.<Land, QLand>createSet("lands", Land.class, QLand.class, PathInits.DIRECT2);

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath name = createString("name");

    public QFarmer(String variable) {
        super(Farmer.class, forVariable(variable));
    }

    public QFarmer(Path<? extends Farmer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFarmer(PathMetadata metadata) {
        super(Farmer.class, metadata);
    }

}

