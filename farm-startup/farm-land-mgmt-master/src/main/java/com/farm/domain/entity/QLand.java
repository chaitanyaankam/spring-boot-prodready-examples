package com.farm.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.farm.domain.entity.Farm;
import com.farm.domain.entity.Farmer;
import com.farm.domain.entity.Land;
import com.farm.domain.entity.LandOwner;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.MapPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QLand is a Querydsl query type for Land
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLand extends EntityPathBase<Land> {

    private static final long serialVersionUID = -416169385L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLand land = new QLand("land");

    public final QAddress address;

    public final StringPath description = createString("description");

    public final SetPath<Farmer, QFarmer> farmers = this.<Farmer, QFarmer>createSet("farmers", Farmer.class, QFarmer.class, PathInits.DIRECT2);

    public final SetPath<Farm, QFarm> farms = this.<Farm, QFarm>createSet("farms", Farm.class, QFarm.class, PathInits.DIRECT2);

    public final MapPath<String, String, StringPath> images = this.<String, String, StringPath>createMap("images", String.class, String.class, StringPath.class);

    public final NumberPath<Integer> landId = createNumber("landId", Integer.class);

    public final QLandSize landSize;

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Integer> numberOfFarms = createNumber("numberOfFarms", Integer.class);

    public final SetPath<LandOwner, QLandOwner> owners = this.<LandOwner, QLandOwner>createSet("owners", LandOwner.class, QLandOwner.class, PathInits.DIRECT2);

    public final StringPath surveyNo = createString("surveyNo");

    public QLand(String variable) {
        this(Land.class, forVariable(variable), INITS);
    }

    public QLand(Path<? extends Land> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLand(PathMetadata metadata, PathInits inits) {
        this(Land.class, metadata, inits);
    }

    public QLand(Class<? extends Land> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.landSize = inits.isInitialized("landSize") ? new QLandSize(forProperty("landSize")) : null;
    }

}

