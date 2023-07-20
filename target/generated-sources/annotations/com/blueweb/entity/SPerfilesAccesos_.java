package com.blueweb.entity;

import com.blueweb.entity.SAccesos;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SPerfilesAccesosPK;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T14:28:53")
@StaticMetamodel(SPerfilesAccesos.class)
public class SPerfilesAccesos_ { 

    public static volatile SingularAttribute<SPerfilesAccesos, SPerfilesAccesosPK> sPerfilesAccesosPK;
    public static volatile SingularAttribute<SPerfilesAccesos, SPerfiles> sPerfiles;
    public static volatile SingularAttribute<SPerfilesAccesos, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<SPerfilesAccesos, Date> fechaServidor;
    public static volatile SingularAttribute<SPerfilesAccesos, SAccesos> sAccesos;

}