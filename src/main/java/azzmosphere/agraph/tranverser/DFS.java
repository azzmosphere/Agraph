package azzmosphere.agraph.tranverser;

import azzmosphere.agraph.Edge;
import azzmosphere.agraph.face.FaceInterface;
import azzmosphere.agraph.face.SimpleCycle;
import azzmosphere.agraph.plane.GraphUtils;
import azzmosphere.agraph.vertices.VertexInterface;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Implementation of the Depth First Search.
 *
 * Created by aaron.spiteri on 16/05/2016.
 */

    /*
    Components = 0;

Enumerate all vertices, if for vertex number i, marks[i] == 0 then

   ++Components;

   Call DFS(i, Components), where DFS is

   DFS(vertex, Components)
   {
       marks[vertex] = Components;
       Enumerate all vertices adjacent to vertex and
       for all vertex j for which marks[j] == 0
           call DFS(j, Components);
   }




1  procedure DFS-iterative(G,v):
2      let S be a stack
3      S.push(v)
4      while S is not empty
5            v = S.pop()
6            if v is not labeled as discovered:
7                label v as discovered
8                for all edges from v to w in G.adjacentEdges(v) do
9                    S.push(w)
     */

public class DFS implements TranverserInterface {

    private ArrayList<VertexInterface> vertices;
    private ArrayList<Integer> adjacencyMatrix;
    private ArrayList<Edge> edges;

    public ArrayList<Integer> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(ArrayList<Integer> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    // @Override
    public ArrayList<VertexInterface> getVertices() {
        return vertices;
    }

    // @Override
    public void setVertices(ArrayList<VertexInterface> vertices) {
        this.vertices = vertices;
    }

    // @Override
    public ArrayList<Integer> getAdjancencyMatrix() {
        return adjacencyMatrix;
    }

    // @Override
    public void setAdjancencyMatrix(ArrayList<Integer> adjancyMatrix) {
        this.adjacencyMatrix = adjancyMatrix;
    }

    private ArrayList<Integer> getAdjacentVertices(int id) {
        ArrayList<Integer> a = new ArrayList<>();

        for (int i = 0;i < adjacencyMatrix.size(); i ++) {
            if (GraphUtils.isAdjacent(id, i, adjacencyMatrix)) {
                a.add(i);
            }
        }
        return a;
    }




    @Override
    public ArrayList<FaceInterface> findFacesForVertex(VertexInterface matcher, ArrayList<VertexInterface> vertices, ArrayList<Integer> adjacencyMatrix) {
//        found = new ArrayList<>();
//        this.vertices = vertices;
//        this.adjacencyMatrix = adjacencyMatrix;
//
//        dfs(matcher.getId(), matcher.getId(), 0, new VertexInterface[this.vertices.size()], 0);

        return null;
    }

    @Override
    public ArrayList<FaceInterface> findAllFaces(ArrayList<VertexInterface> vertexInterfaces, ArrayList<Integer> adjacencyMatrix) {
        return null;
    }

    /**
     * find all sub graphs using findAllFaces.
     *
     * @return
     */
    //@Override
    public ArrayList<FaceInterface> findAllFaces() {
        ArrayList<FaceInterface> found = new ArrayList<>();
        TreeSet<Edge> discoveredEdges = new TreeSet<>();

        dfs(vertices.get(0).getId(), vertices.get(0).getId(), found, 0, new ArrayList<>(), discoveredEdges, true);

        return found;
    }

    /*
    1  procedure DFS(G,v):
    2      label v as discovered
    3      for all edges from v to w in G.adjacentEdges(v) do
    4          if vertex w is not labeled as discovered then
    5              recursively call DFS(G,w)
    */

    private int dfs(int v, int required, ArrayList<FaceInterface> found, int discovered, ArrayList<Integer> stack, TreeSet<Edge> discoveredEdges, boolean isFirst) {

        if (GraphUtils.isMarked(required, discovered)) {
            return discovered;
        }
        else if (v == required && isFirst == false) {
            buildFace(found, v, stack);
            return discovered;
        }

        ArrayList<Integer> adjacentNodes = getAdjacentVertices(v);

        for (int w : adjacentNodes) {
            Edge e = getEdge(vertices.get(v), vertices.get(w), edges);
            if (!discoveredEdges.contains(e)) {
                discoveredEdges.add(e);
                stack.add(v);
                discovered = GraphUtils.markVertex(v, discovered);
                discovered = dfs(w, required, found, discovered, (ArrayList<Integer>) stack.clone(), discoveredEdges, false);
            }
        }

        return discovered;
    }

    private Edge getEdge(VertexInterface v1, VertexInterface v2, ArrayList<Edge> edges ) {
        for (Edge e : edges) {
            if (e.containsNodes(v1, v2)) {
                return e;
            }
        }
        return null;
    }

//    private int dfs(int v, int required, ArrayList<FaceInterface> found, int discovered, int previous, ArrayList<Integer> stack) {
//
//        // Face found.
//        if (GraphUtils.isMarked(v, discovered)) {
//            buildFace(found, v, stack);
//            return discovered;
//        }
//        discovered = GraphUtils.markVertex(v, discovered);
//        ArrayList<Integer> adjacentNodes = getAdjacentVertices(v);
//        stack.add(v);
//
//        for (int w : adjacentNodes) {
//            int foundCount = found.size();
//            if (w != previous) {
//                //discovered = dfs(found, discovered, w, v, (ArrayList<Integer>) stack.clone());
//            }
//            if (foundCount != found.size()) {
//                stack = new ArrayList<>();
//                stack.add(v);
//            }
//        }
//        return discovered;
//    }

    /**
     * build face.
     *
     * @param found all the found faces.
     * @param marker starting id of face
     * @param stack vertices in
     */
    private void buildFace(ArrayList<FaceInterface> found, int marker, ArrayList<Integer> stack) {
        //TODO: Remove the direct reference to SimpleCycle.
        FaceInterface face = new SimpleCycle();
        boolean markerSet = false;

        for (int id : stack) {
            if (id == marker) {
                markerSet = true;
            }
            if (markerSet) {
                face.addVertex(vertices.get(id));
            }
        }
        found.add(face);
    }
}
