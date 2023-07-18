package com.blueweb.entity;

import com.blueweb.entity.CAutomata;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.CClientes;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.CTipoTelefono;
import com.blueweb.entity.MArchivoLote;
import com.blueweb.entity.SUsuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-14T12:27:03")
@StaticMetamodel(HActivacion.class)
public class HActivacion_ { 

    public static volatile SingularAttribute<HActivacion, SUsuarios> idUsuario;
    public static volatile SingularAttribute<HActivacion, String> claveDistribuidor;
    public static volatile SingularAttribute<HActivacion, CCiudad> idCiudad;
    public static volatile SingularAttribute<HActivacion, String> descripcionTipo;
    public static volatile SingularAttribute<HActivacion, String> iccid;
    public static volatile SingularAttribute<HActivacion, String> claveAutomata;
    public static volatile SingularAttribute<HActivacion, CClientes> idCliente;
    public static volatile SingularAttribute<HActivacion, Date> fechaPeticion;
    public static volatile SingularAttribute<HActivacion, CRegion> idRegion;
    public static volatile SingularAttribute<HActivacion, MArchivoLote> idArchivo;
    public static volatile SingularAttribute<HActivacion, Long> id;
    public static volatile SingularAttribute<HActivacion, String> telefono;
    public static volatile SingularAttribute<HActivacion, CDistribuidor> idDistribuidor;
    public static volatile SingularAttribute<HActivacion, String> nombreRegion;
    public static volatile SingularAttribute<HActivacion, Long> idActivacion;
    public static volatile SingularAttribute<HActivacion, CAutomata> idAutomata;
    public static volatile SingularAttribute<HActivacion, String> distribuidor;
    public static volatile SingularAttribute<HActivacion, Date> fechaRespuesta;
    public static volatile SingularAttribute<HActivacion, String> cliente;
    public static volatile SingularAttribute<HActivacion, Long> monto;
    public static volatile SingularAttribute<HActivacion, String> ciudad;
    public static volatile SingularAttribute<HActivacion, String> imei;
    public static volatile SingularAttribute<HActivacion, CTipoTelefono> idTipoTelefonia;
    public static volatile SingularAttribute<HActivacion, String> respuestaAplicacion;
    public static volatile SingularAttribute<HActivacion, Date> fechaServidor;

}