package org.example.expert.domain.todo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = -1664369315L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final org.example.expert.domain.common.entity.QTimestamped _super = new org.example.expert.domain.common.entity.QTimestamped(this);

    public final ListPath<org.example.expert.domain.comment.entity.Comment, org.example.expert.domain.comment.entity.QComment> comments = this.<org.example.expert.domain.comment.entity.Comment, org.example.expert.domain.comment.entity.QComment>createList("comments", org.example.expert.domain.comment.entity.Comment.class, org.example.expert.domain.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<org.example.expert.domain.manager.entity.Manager, org.example.expert.domain.manager.entity.QManager> managers = this.<org.example.expert.domain.manager.entity.Manager, org.example.expert.domain.manager.entity.QManager>createList("managers", org.example.expert.domain.manager.entity.Manager.class, org.example.expert.domain.manager.entity.QManager.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath title = createString("title");

    public final org.example.expert.domain.user.entity.QUser user;

    public final StringPath weather = createString("weather");

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.example.expert.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

