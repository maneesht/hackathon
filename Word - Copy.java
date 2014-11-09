package word;
public class Word {
    public int frequency;
    String word;
    public Word()
    {
        word="";
        frequency=0;
    }
    public Word(String wool)
    {
        word=wool;
        frequency=0;
    }
    public Word(String wool,int freq)
    {
        word=wool;
        frequency=freq;
    }
}
