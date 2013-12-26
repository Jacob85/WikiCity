package il.ac.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryException extends Exception
{
    public QueryException(String detailMessage)
    {
        super(detailMessage);
    }

    public QueryException()
    {
    }
}
