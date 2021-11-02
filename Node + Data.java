class Node{
    int Index;
    int[] Color;
    int Radius;
    LinkedList<Node> Edges = new LinkedList<Node> ()
    Data Data;
    NodeSymbol NodeSymbol;
    EdgeSymbol EdgeSymbol;

    Node(int Index, int[] Color, int Radius, LinkedList Edges){
        this.Index = Index;
        this.Color = Color;
        this.Radius = Radius;
        this.Edges = Edges;
        NodeSymbol = new NodeSymbol(Index, Color, Radius);
        EdgeSymbol = new EdgeSymbol(Node[] ToConnect, Weight);
    }

    public void ChangeColor(int[] Color){
        this.Color = Color;
    }

    public int[] GiveColor(){
        return Color;
    }

    public int GiveIndex(){
        return Index
    }

    public LinkedList<Node> GiveEdges(){
        return Edges;
    }

    public Data GiveData(){
        return Data;
    }

    public int[] GivePosition(){
        return Position;
    }

    public void Draw(){
        NodeSymbol.Draw()
        EdgeSymbol.Draw()
    }
}

interface Data{
    
}
