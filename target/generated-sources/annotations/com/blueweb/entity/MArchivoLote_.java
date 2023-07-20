package com.blueweb.entity;

import com.blueweb.entity.HActivacion;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T14:28:53")
@StaticMetamodel(MArchivoLote.class)
public class MArchivoLote_ { 

    public static volatile SingularAttribute<MArchivoLote, String> nombreArchivo;
    public static volatile SingularAttribute<MArchivoLote, String> tipo;
    public static volatile SingularAttribute<MArchivoLote, byte[]> archivo;
    public static volatile SingularAttribute<MArchivoLote, Date> fechaAlta;
    public static volatile SingularAttribute<MArchivoLote, SUsuarios> idUsuario;
    public static volatile CollectionAttribute<MArchivoLote, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<MArchivoLote, String> archivoGenerado;
    public static volatile SingularAttribute<MArchivoLote, Long> idArchivo;
    public static volatile SingularAttribute<MArchivoLote, Date> fechaServidor;

}