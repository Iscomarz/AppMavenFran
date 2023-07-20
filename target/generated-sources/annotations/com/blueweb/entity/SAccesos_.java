package com.blueweb.entity;

import com.blueweb.entity.SPerfilesAccesos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T14:28:53")
@StaticMetamodel(SAccesos.class)
public class SAccesos_ { 

    public static volatile SingularAttribute<SAccesos, Date> fechaBaja;
    public static volatile SingularAttribute<SAccesos, String> nombreAcceso;
    public static volatile CollectionAttribute<SAccesos, SPerfilesAccesos> sPerfilesAccesosCollection;
    public static volatile SingularAttribute<SAccesos, Short> orden;
    public static volatile SingularAttribute<SAccesos, Integer> idAcceso;
    public static volatile SingularAttribute<SAccesos, Date> fechaServidor;
    public static volatile SingularAttribute<SAccesos, Boolean> activo;

}