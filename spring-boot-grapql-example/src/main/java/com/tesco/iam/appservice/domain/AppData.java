package com.tesco.iam.appservice.domain;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Graph
public class AppData {

    @Id
    private String id;

    public AppData() {
        this.edges = new ArrayList<>();
        this.vertexes = new ArrayList<>();
    }

    @EdgeSet
    private List<Object> edges;

    @VertexSet
    private List<Object> vertexes;

    public List<Object> getEdges() {
        return edges;
    }

    public List<Object> getVertexes() {
        return vertexes;
    }
}