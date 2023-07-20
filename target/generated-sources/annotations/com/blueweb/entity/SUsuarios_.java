package com.blueweb.entity;

import com.blueweb.entity.CCiudad;
import com.blueweb.entity.CClientes;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.HActivacion;
import com.blueweb.entity.MArchivoLote;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SPerfilesAccesos;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-20T14:28:53")
@StaticMetamodel(SUsuarios.class)
public class SUsuarios_ { 

    public static volatile SingularAttribute<SUsuarios, Date> fechaBaja;
    public static volatile CollectionAttribute<SUsuarios, MArchivoLote> mArchivoLoteCollection;
    public static volatile SingularAttribute<SUsuarios, Date> fechaAlta;
    public static volatile CollectionAttribute<SUsuarios, SPerfilesAccesos> sPerfilesAccesosCollection;
    public static volatile SingularAttribute<SUsuarios, Integer> idUsuario;
    public static volatile CollectionAttribute<SUsuarios, CDistribuidor> cDistribuidorCollection;
    public static volatile SingularAttribute<SUsuarios, String> nombreUsuario;
    public static volatile CollectionAttribute<SUsuarios, CCiudad> cCiudadCollection;
    public static volatile SingularAttribute<SUsuarios, Date> ultimaSesion;
    public static volatile CollectionAttribute<SUsuarios, CRegion> cRegionCollection;
    public static volatile SingularAttribute<SUsuarios, String> password;
    public static volatile SingularAttribute<SUsuarios, CClientes> idCliente;
    public static volatile CollectionAttribute<SUsuarios, SUsuarios> sUsuariosCollection;
    public static volatile SingularAttribute<SUsuarios, SPerfiles> idPerfil;
    public static volatile CollectionAttribute<SUsuarios, SPerfiles> sPerfilesCollection;
    public static volatile CollectionAttribute<SUsuarios, HActivacion> hActivacionCollection;
    public static volatile SingularAttribute<SUsuarios, String> correo;
    public static volatile SingularAttribute<SUsuarios, String> usuario;
    public static volatile SingularAttribute<SUsuarios, SUsuarios> idUsuarioModifica;
    public static volatile CollectionAttribute<SUsuarios, CClientes> cClientesCollection;
    public static volatile SingularAttribute<SUsuarios, Date> fechaServidor;
    public static volatile SingularAttribute<SUsuarios, Boolean> activo;

}