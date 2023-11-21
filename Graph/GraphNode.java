package SiAOD.Graph;
import java.util.ArrayList;
public class GraphNode // Класс, представляющий собой узел в графе
{
    private final int data; // информация, хранящаяся в узле графа
    private int countEdge = 0; // счетчик добавленных рёбер к данномму узлу
    private final ArrayList<GraphEdge> edges = new ArrayList<>(); // Список рёбер, исходящих из данного узла
    public GraphNode(int data) // Конструктор
    {
        this.data = data;
    }
    public int getData()
    {
        return data;
    }
    public int getCountEdge()
    {
        return countEdge++;
    }
    public ArrayList<GraphEdge> getEdges()
    {
        return edges;
    }
}