package com.blueweb.entity;

import com.blueweb.entity.SPerfilesAccesos;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T12:27:03")
@StaticMetamodel(SPerfiles.class)
public class SPerfiles_ { 

    public static volatile SingularAttribute<SPerfiles, String> descripcion;
    public static volatile SingularAttribute<SPerfiles, Date> fechaBaja;
    public static volatile SingularAttribute<SPerfiles, Date> fechaAlta;
    public static volatile CollectionAttribute<SPerfiles, SUsuarios> sUsuariosCollection;
    public static volatile SingularAttribute<SPerfiles, Integer> idPerfil;
    public static volatile CollectionAttribute<SPerfiles, SPerfilesAccesos> sPerfilesAccesosCollection;
    public static volatile SingularAttribute<SPerfiles, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<SPerfiles, String> nombrePerfil;
    public static volatile SingularAttribute<SPerfiles, Date> fechaServidor;
    public static volatile SingularAttribute<SPerfiles, Boolean> activo;

}