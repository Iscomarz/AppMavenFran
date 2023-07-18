package com.blueweb.entity;

import com.blueweb.entity.HActivacion;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T12:27:03")
@StaticMetamodel(CClientes.class)
public class CClientes_ { 

    public static volatile SingularAttribute<CClientes, Date> fechaBaja;
    public static volatile SingularAttribute<CClientes, String> numExt;
    public static volatile SingularAttribute<CClientes, Date> fechaAlta;
    public static volatile SingularAttribute<CClientes, String> calle;
    public static volatile SingularAttribute<CClientes, String> telContacto;
    public static volatile SingularAttribute<CClientes, String> numCliente;
    public static volatile SingularAttribute<CClientes, String> cp;
    public static volatile SingularAttribute<CClientes, String> rfc;
    public static volatile SingularAttribute<CClientes, String> colonia;
    public static volatile SingularAttribute<CClientes, String> clavePlaza;
    public static volatile SingularAttribute<CClientes, Long> idCliente;
    public static volatile SingularAttribute<CClientes, String> nombreCliente;
    public static volatile CollectionAttribute<CClientes, SUsuarios> sUsuariosCollection;
    public static volatile CollectionAttribute<CClientes, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<CClientes, SUsuarios> idUsuarioModifica;
    public static volatile SingularAttribute<CClientes, Date> fechaServidor;
    public static volatile SingularAttribute<CClientes, Boolean> activo;

}