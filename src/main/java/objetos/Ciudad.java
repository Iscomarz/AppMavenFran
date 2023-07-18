
package objetos;
 


/**
*
* @author Blueweb
* Objeto
*/

public class Ciudad {

    private int id;
    private String descripcion;
    private String codigo;
    private int lada;


    public Ciudad(int id, String descripcion, String codigo, int lada) {
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.lada = lada;
    }

    public Ciudad() {
        
    }

 

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

 

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

 

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

 

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

 

    /**
     * @return the estadoCliente
     */
    public String getCodigo() {
        return codigo;
    }

 

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

 

    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }

 

    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
    }
}
