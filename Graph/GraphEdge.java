package SiAOD.Graph;
public class GraphEdge // Класс, представляющий собой ребро в графе
{
    private final int weight; // Вес ребра
    private final GraphNode graphNode; // Т.к граф ориентированный, мы храним здесь ссылку на узел, куда ведет ребро графа
    public GraphEdge(int weight, GraphNode graphNode) // Конструктор
    {
        this.weight = weight;
        this.graphNode = graphNode;
    }
    public int getWeight()
    { return weight; }
    public GraphNode getGraphNode()
    {
        return graphNode;
    }
}
