package com.blueweb.entity;

import com.blueweb.entity.CTelefonia;
import com.blueweb.entity.HActivacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T12:27:03")
@StaticMetamodel(CTipoTelefono.class)
public class CTipoTelefono_ { 

    public static volatile SingularAttribute<CTipoTelefono, String> descripcion;
    public static volatile SingularAttribute<CTipoTelefono, String> clave;
    public static volatile CollectionAttribute<CTipoTelefono, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CTipoTelefono, Long> id;
    public static volatile SingularAttribute<CTipoTelefono, CTelefonia> idTelefonia;
    public static volatile SingularAttribute<CTipoTelefono, Date> fechaServidor;
    public static volatile SingularAttribute<CTipoTelefono, Boolean> validaImei;
    public static volatile SingularAttribute<CTipoTelefono, Boolean> activo;

}