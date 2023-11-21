package SiAOD.Graph;
import java.util.*;

public class Graph // Класс, представляющий собой граф
{
    private final HashMap<Integer, GraphNode> graphHashMap; // Хеш-таблица, в которой содержатся все узлы графа
    public Graph()
    {
        graphHashMap = new HashMap<>();
    }
    public boolean addGraph(int data) // Метод добавления узлов в граф
    {
        if (graphHashMap.containsKey(data)) // Если узла с таким ключём нет в хеш-таблице
            return false;
        GraphNode graphNode = new GraphNode(data); // Создаём новый узел графа и передаём в конструктор информацию
        graphHashMap.put(data, graphNode); // Добавляем новый узел в хеш-таблицу (граф)
        return true;
    }
    // Метод добавления связей между узлами в графе, т.е метод добалвения рёбер графа
    public boolean addEdge(int from, int to, int weight) throws IllegalArgumentException
    {
        if (weight <= 0) // Некорректный вес ребра
            throw new IllegalArgumentException("Negative edge weight");
        if (!graphHashMap.containsKey(from)) // Проверка на существование исходного узла в графе
            return false;
        if (graphHashMap.get(to) == null) // Если мы хотим добавить ребро к узлу, которого нет, нужно сначала его добавить
        {
            System.out.println("Элемента, на который указывает ребро нет");
            addGraph(to); // Добавление узла
        }
        GraphNode graphNode = graphHashMap.get(from); // Получение из хеш-таблицы узла, от которого будет следовать ребро
        GraphEdge graphEdge = new GraphEdge(weight, graphHashMap.get(to)); // Добавление ребра от узла к узлу
        graphNode.getEdges().add(graphNode.getCountEdge(), graphEdge); // Добавляем в массив рёбер новое ребро
        return true;
    }
    /*public int decisionTreeWay(GraphNode start, GraphNode end)
    {
        if (!graphHashMap.containsValue(start) || !graphHashMap.containsValue(end))
            throw new NullPointerException("One of the nodes of the graph does not exist");
        if (start == end || start.getEdges().isEmpty())
            return 0;
        int[][] matrix = new int[graphHashMap.size()][graphHashMap.size()];
        GraphNode current = start;
        int summ = 0;
        Stack<GraphNode> stack = new Stack<>();
        stack.push(current);
        Stack<List<Integer>> ways = new Stack<>();
        List<Integer> way = new ArrayList<>();
        //HashMap<Integer, GraphNode> passedNodes = new HashMap<>();
        //passedNodes.put(current.getData(), current);
        while (!stack.isEmpty())
        {
            current = stack.peek();
            for (int i = 0; i < current.getEdges().size(); i++)
            {
                stack.push(current.getEdges().get(i).getGraphNode());
                way.add(current.getEdges().get(i).getGraphNode().getData());
            }
            if (current == end)
                ways.add(way);
        }
        return 0;
    }*/
    private DecisionTreeNode buildDecisionTree(int startData) throws NullPointerException // Метод построения дерева решений
    {
        if (!graphHashMap.containsKey(startData)) // Если такой вершины не существует в графе
        {
            System.out.println("Стартовая вершина не существует");
            throw new NullPointerException("There is no graph node with such a key");
        }
        DecisionTreeNode root = new DecisionTreeNode(startData, 0); // Создаём начальный узел дерева
        Queue<DecisionTreeNode> queue = new LinkedList<>(); // Создаём очередь для итеративного добавления детей к узлам
        queue.add(root); // Добавляем корень данного дерева в очередь
        while (!queue.isEmpty()) // Пока очередь не пуста
        {
            DecisionTreeNode current = queue.poll(); // Достаём узел из очереди
            GraphNode graphNode = graphHashMap.get(current.getGraphData()); // Получаем узел графа с такой информацией
            for (GraphEdge edge : graphNode.getEdges()) // Перебираем рёбра данного узла
            {
                // Создаём новый узел дерева и добавляем в него информацию, а также вес ребра, которое входит в данный узел
                DecisionTreeNode node = new DecisionTreeNode(edge.getGraphNode().getData(), edge.getWeight());
                current.getChilds().add(node); // Добавляем в список детей данный узел дерева
                queue.add(node); // Добавляем данный узел в очередь
            }
        }
        return root; // Возврат узла, который является корнем дерева, начиная от startData
    }
    public int decisionTreeWay(int start, int end) // Метод поиска кратчайшего пути в дереве решений
    {
        if (!graphHashMap.containsKey(start) || !graphHashMap.containsKey(end)) // Проверка на существование узлов в графе
            throw new NullPointerException("One of the nodes of the graph does not exist");
        DecisionTreeNode root = buildDecisionTree(start); // Получаем дерево начиная от заданной вершины
        if (root.getChilds().isEmpty() || root.getGraphData() == end) // Условие выхода из рекурсии при поиске путя
            return 0;
        int minWay = root.getChilds().get(0).getGraphWeight(); // Текущий начальный путь
        for (DecisionTreeNode node : root.getChilds()) // Поиск минимального путя среди узлов потомков
            minWay = Math.min(minWay, node.getGraphWeight()); // Идём по ветке с минимальным весом
        for (DecisionTreeNode node : root.getChilds()) // Перебираем узлы дерева уже на следующем уровне
        {
            if (minWay == node.getGraphWeight()) // Если дошли до узла, у которого входящая ветка была минимальной
                minWay += decisionTreeWay(node.getGraphData(), end); // Рекурсивно доходим до конца пути
        }
        return minWay; // Возврат суммы минимального пути
    }
    public void printTree(int numberNode) // Метод, выводящий в консоль в иерархическом порядке узлы дерева
    {
        if (!graphHashMap.containsKey(numberNode)) // Условие введённых данных
            return;
        DecisionTreeNode current = buildDecisionTree(numberNode); // Построение дерева от текущей вершины графа
        Stack<DecisionTreeNode> stack = new Stack<>(); // Стек для перебора в цикле узлов дерева
        Stack<Integer> levels = new Stack<>(); // Стек для порядка иерархии в дереве
        stack.push(current); // Добавляем корень в стек
        levels.push(0); // Корень самый главный
        while (!stack.isEmpty()) // Пока стек не пустой
        {
            DecisionTreeNode node = stack.pop(); // Достаём из стека узел дерева
            int level = levels.pop(); // Достаем "количество отступов" из стека
            for (int i = 0; i < level; i++) // Вывод отступов для иерархиризации
                System.out.print("  ");
            System.out.println(node.getGraphData()); // Вывод информации узла
            for (DecisionTreeNode child : node.getChilds()) // Перебор по дочерним узлам
            {
                stack.push(child); // Добавляем их в стек
                levels.push(++level); // Добавляем отступы
            }
        }
    }
    public void printGraph()
    {
        for (GraphNode graphNode : graphHashMap.values())
            System.out.println(graphNode.getData());
    }
}