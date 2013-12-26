package il.ac.services;

/**
 * Created with IntelliJ IDEA.
 * User: Jacob
 * Date: 12/26/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QueryWikipediaCallback <T>
{
   public void done(T returnedObject, Exception e);
}
