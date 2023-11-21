package SiAOD.Graph;
import java.util.ArrayList;
public class DecisionTreeNode // Класс, реализующий узел дерева решений
{
    private final int graphData; // Информация из узла графа
    private final int graphWeight; // Вес входящего в узел графа ребра
    private final ArrayList<DecisionTreeNode> childs; // Список всех детей у данного узла дерева
    DecisionTreeNode(int graphData, int graphWeight) // Конструктор
    {
        this.graphData = graphData;
        this.graphWeight = graphWeight;
        this.childs = new ArrayList<>();
    }
    public int getGraphData()
    {
        return graphData;
    }

    public int getGraphWeight()
    {
        return graphWeight;
    }
    public ArrayList<DecisionTreeNode> getChilds()
    {
        return childs;
    }
}
