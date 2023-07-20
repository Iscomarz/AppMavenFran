package com.blueweb.entity;

import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.HActivacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T14:28:53")
@StaticMetamodel(CAutomata.class)
public class CAutomata_ { 

    public static volatile SingularAttribute<CAutomata, String> clave;
    public static volatile SingularAttribute<CAutomata, Date> fechaAlta;
    public static volatile CollectionAttribute<CAutomata, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CAutomata, CRegion> idRegion;
    public static volatile SingularAttribute<CAutomata, Long> idAutomata;
    public static volatile CollectionAttribute<CAutomata, CDistribuidor> cDistribuidorCollection;
    public static volatile SingularAttribute<CAutomata, Boolean> activo;

}