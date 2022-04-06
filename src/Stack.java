import java.nio.BufferUnderflowException;

public class Stack <E>
{
    private class Node <E>
    {
        E data;
        Node next;

        public Node(E stuff)
        {
            data = stuff;
            next = null;
        }
    }

    private Node<E> mTop;
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

    public E pop()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
        E stuff = mTop.data;
        mTop = mTop.next;
        Size--;
        return stuff;
    }

    public void push(E example)
    {
        Node n = new Node(example);
        n.next = mTop;
        mTop = n;
        Size++;
    }

    public E peek()
    {
        if (isEmpty())
            throw new BufferUnderflowException();
            return mTop.data;
    }
}

