import java.nio.BufferUnderflowException;

public class Stack <Athlete>
{

    @Override
    public String toString() {
        return "Stack{" +
                "mTop=" + mTop.data +
                '}';
    }

    private class Node <Athlete>
    {
        Athlete data;
        Node next;

        public Node(Athlete stuff)
        {
            data = stuff;
            next = null;
        }
    }

    private Node<Athlete> mTop;
    private int Size;

    public Stack()
    {
        mTop = null;
        Size = 0;
    }

    public boolean isEmpty()
    {
        return mTop == null;
    }

    public int getSize()
    {
        return Size;
    }

    public Athlete pop()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
            Athlete stuff = mTop.data;
            mTop = mTop.next;
        Size--;
        return stuff;
    }

    public void push(Athlete example)
    {
        Node n = new Node(example);
        n.next = mTop;
        mTop = n;
        Size++;
    }

    public Athlete peek()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
            return mTop.data;
    }

    public static void main(String[] args) {
        Stack test = new Stack();

        test.push("hi");
        test.push("ok");
        test.push("try");

        System.out.println(test);
        System.out.println(test.peek());
        System.out.println(test.pop());
    }
}

