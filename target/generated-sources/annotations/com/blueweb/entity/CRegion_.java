package com.blueweb.entity;

import com.blueweb.entity.CAutomata;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CTelefonia;
import com.blueweb.entity.HActivacion;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T12:27:03")
@StaticMetamodel(CRegion.class)
public class CRegion_ { 

    public static volatile SingularAttribute<CRegion, String> plaza;
    public static volatile SingularAttribute<CRegion, Date> fechaBaja;
    public static volatile SingularAttribute<CRegion, String> nombreRegion;
    public static volatile SingularAttribute<CRegion, Date> fechaAlta;
    public static volatile CollectionAttribute<CRegion, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CRegion, Long> idRegion;
    public static volatile CollectionAttribute<CRegion, CDistribuidor> cDistribuidorCollection;
    public static volatile SingularAttribute<CRegion, SUsuarios> idUsuarioModifica;
    public static volatile CollectionAttribute<CRegion, CTelefonia> cTelefoniaCollection;
    public static volatile SingularAttribute<CRegion, Date> fechaServidor;
    public static volatile SingularAttribute<CRegion, Boolean> activo;
    public static volatile CollectionAttribute<CRegion, CAutomata> cAutomataCollection;

}