/*
 * Copyright (c) 1998-2018 University Corporation for Atmospheric Research/Unidata
 * See LICENSE for license information.
 */

package ucar.nc2.jni.netcdf;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

/**
 * JNA access to Netcdf-4 C Library, using JNI to shared C library.
 * Just the functions actually used.
 *
 * I suspect that this whole construct:
 *        synchronized (Nc4Lock.class) {ce();
 *            return cxs(nc4.nc_xxx());
 *        }
 * can be significantly simplified in Java 9.
 *
 * @author dmh
 * @since June 11, 2018
 */
class Nc4wrapper implements Nc4prototypes
{

    static private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Nc4wrapper.class);

    static int counter;

    static protected void err(String cc, int c) {log.error("Serial failure: "+cc+": counter != "+c+"\n");}

    static protected void ce() {if(counter != 0) err("ce",0); counter = 1;}
    static protected String cx(String x) {if(counter != 1) err("cxs",1); counter=0; return x;};
    static protected int cx(int x) {if(counter != 1) err("cxs",1); counter=0; return x;};

    private Nc4prototypes nc4 = null;

    public Nc4wrapper(Nc4prototypes nc4) {this.nc4 = nc4;}

    // Begin API Override

    @Override
    public synchronized
    String nc_inq_libvers()
    {ce(); return cx(nc4.nc_inq_libvers());}

    @Override
    public synchronized
    String nc_strerror(int ncerr)
    {ce(); return cx(nc4.nc_strerror(ncerr));}

    @Override
    public synchronized
    int nc_open(String path, int mode, IntByReference ncidp)
    {ce(); return cx(nc4.nc_open(path,mode, ncidp));}

    @Override
    public synchronized
    int nc_close(int ncid)
    {ce(); return cx(nc4.nc_close(ncid));}

    @Override
    public synchronized
    int nc_inq_format(int ncid, IntByReference formatp)
    {ce(); return cx(nc4.nc_inq_format(ncid, formatp));}

    @Override
    public synchronized
    int nc_inq_format_extended(int ncid, IntByReference formatp, IntByReference modep)
    {ce(); return cx(nc4.nc_inq_format_extended(ncid, formatp, modep));}

    @Override
    public synchronized
    int nc_inq_grps(int ncid, IntByReference numgrps, int[] ncids)
    {ce(); return cx(nc4.nc_inq_grps(ncid, numgrps, ncids));}

    @Override
    public synchronized
    int nc_inq_grpname(int ncid, byte[] name)
    {ce(); return cx(nc4.nc_inq_grpname(ncid, name));}

    @Override
    public synchronized
    int nc_inq_grpname_full(int ncid, SizeTByReference lenp, byte[] full_name)
    {ce(); return cx(nc4.nc_inq_grpname_full(ncid, lenp, full_name));}

    @Override
    public synchronized
    int nc_inq_grpname_len(int ncid, SizeTByReference lenp)
    {ce(); return cx(nc4.nc_inq_grpname_len(ncid, lenp));}

    @Override
    public synchronized
    int nc_inq_ndims(int ncid, IntByReference ndimsp)
    {ce(); return cx(nc4.nc_inq_ndims(ncid, ndimsp));}

    @Override
    public synchronized
    int nc_inq_unlimdims(int ncid, IntByReference nunlimdimsp, int[] unlimdimidsp)
    {ce(); return cx(nc4.nc_inq_unlimdims(ncid, nunlimdimsp, unlimdimidsp));}

    @Override
    public synchronized
    int nc_inq_dimids(int ncid, IntByReference ndims, int[] dimids, int include_parents)
    {ce(); return cx(nc4.nc_inq_dimids(ncid, ndims, dimids,include_parents));}

    @Override
    public synchronized
    int nc_inq_dim(int ncid, int dimid, byte[] name, SizeTByReference lenp)
    {ce(); return cx(nc4.nc_inq_dim(ncid,dimid, name, lenp));}

    @Override
    public synchronized
    int nc_inq_dimname(int ncid, int dimid, byte[] name)
    {ce(); return cx(nc4.nc_inq_dimname(ncid,dimid, name));}

    @Override
    public synchronized
    int nc_inq_natts(int ncid, IntByReference nattsp)
    {ce(); return cx(nc4.nc_inq_natts(ncid, nattsp));}

    @Override
    public synchronized
    int nc_inq_attname(int ncid, int varid, int attnum, byte[] name)
    {ce(); return cx(nc4.nc_inq_attname(ncid,varid,attnum, name));}

    @Override
    public synchronized
    int nc_inq_atttype(int ncid, int varid, String name, IntByReference xtypep)
    {ce(); return cx(nc4.nc_inq_atttype(ncid,varid,name, xtypep));}

    @Override
    public synchronized
    int nc_inq_attlen(int ncid, int varid, String name, SizeTByReference lenp)
    {ce(); return cx(nc4.nc_inq_attlen(ncid,varid,name, lenp));}

    @Override
    public synchronized
    int nc_get_att_double(int ncid, int varid, String name, double[] ip)
    {ce(); return cx(nc4.nc_get_att_double(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_float(int ncid, int varid, String name, float[] ip)
    {ce(); return cx(nc4.nc_get_att_float(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_int(int ncid, int varid, String name, int[] ip)
    {ce(); return cx(nc4.nc_get_att_int(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_uint(int ncid, int varid, String name, int[] ip)
    {ce(); return cx(nc4.nc_get_att_uint(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_longlong(int ncid, int varid, String name, long[] ip)
    {ce(); return cx(nc4.nc_get_att_longlong(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_ulonglong(int ncid, int varid, String name, long[] ip)
    {ce(); return cx(nc4.nc_get_att_ulonglong(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_schar(int ncid, int varid, String name, byte[] ip)
    {ce(); return cx(nc4.nc_get_att_schar(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_uchar(int ncid, int varid, String name, byte[] ip)
    {ce(); return cx(nc4.nc_get_att_uchar(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_ubyte(int ncid, int varid, String name, byte[] ip)
    {ce(); return cx(nc4.nc_get_att_ubyte(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_short(int ncid, int varid, String name, short[] ip)
    {ce(); return cx(nc4.nc_get_att_short(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_ushort(int ncid, int varid, String name, short[] ip)
    {ce(); return cx(nc4.nc_get_att_ushort(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_text(int ncid, int varid, String name, byte[] ip)
    {ce(); return cx(nc4.nc_get_att_text(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att_string(int ncid, int varid, String name, String[] ip)
    {ce(); return cx(nc4.nc_get_att_string(ncid,varid,name, ip));}

    @Override
    public synchronized
    int nc_get_att(int ncid, int varid, String name, byte[] bbuff)
    {ce(); return cx(nc4.nc_get_att(ncid,varid,name, bbuff));}

    @Override
    public synchronized
    int nc_inq_nvars(int ncid, IntByReference nvarsp)
    {ce(); return cx(nc4.nc_inq_nvars(ncid, nvarsp));}

    @Override
    public synchronized
    int nc_inq_varids(int ncid, IntByReference nvars, int[] varids)
    {ce(); return cx(nc4.nc_inq_varids(ncid, nvars, varids));}

    @Override
    public synchronized
    int nc_inq_var(int ncid, int varid, byte[] name, IntByReference xtypep, IntByReference ndimsp, int[] dimidsp, IntByReference nattsp)
    {ce(); return cx(nc4.nc_inq_var(ncid,varid, name, xtypep, ndimsp, dimidsp, nattsp));}

    @Override
    public synchronized
    int nc_inq_varid(int ncid, byte[] name, IntByReference varidp)
    {ce(); return cx(nc4.nc_inq_varid(ncid, name, varidp));}

    @Override
    public synchronized
    int nc_inq_vardimid(int ncid, int varid, int[] dimidsp)
    {ce(); return cx(nc4.nc_inq_vardimid(ncid,varid, dimidsp));}

    @Override
    public synchronized
    int nc_inq_varnatts(int ncid, int varid, IntByReference nattsp)
    {ce(); return cx(nc4.nc_inq_varnatts(ncid,varid, nattsp));}

    @Override
    public synchronized
    int nc_inq_typeids(int ncid, IntByReference ntypes, int[] typeids)
    {ce(); return cx(nc4.nc_inq_typeids(ncid, ntypes, typeids));}

    @Override
    public synchronized
    int nc_inq_type(int ncid, int xtype, byte[] name, SizeTByReference sizep)
    {ce(); return cx(nc4.nc_inq_type(ncid,xtype, name, sizep));}

    @Override
    public synchronized
    int nc_inq_user_type(int ncid, int xtype, byte[] name, SizeTByReference sizep, IntByReference baseType, SizeTByReference nfieldsp, IntByReference classp)
    {ce(); return cx(nc4.nc_inq_user_type(ncid,xtype, name, sizep, baseType, nfieldsp, classp));}

    @Override
    public synchronized
    int nc_inq_enum(int ncid, int xtype, byte[] name, IntByReference baseType, SizeTByReference base_sizep, SizeTByReference num_membersp)
    {ce(); return cx(nc4.nc_inq_enum(ncid,xtype, name, baseType, base_sizep, num_membersp));}

    @Override
    public synchronized
    int nc_inq_enum_member(int ncid, int xtype, int idx, byte[] name, IntByReference value)
    {ce(); return cx(nc4.nc_inq_enum_member(ncid,xtype,idx, name, value));}

    @Override
    public synchronized
    int nc_inq_opaque(int ncid, int xtype, byte[] name, SizeTByReference sizep)
    {ce(); return cx(nc4.nc_inq_opaque(ncid,xtype, name, sizep));}

    @Override
    public synchronized
    int nc_get_var(int ncid, int varid, byte[] buf)
    {ce(); return cx(nc4.nc_get_var(ncid,varid, buf));}

    @Override
    public synchronized
    int nc_get_var_text(int ncid, int varid, byte[] op)
    {ce(); return cx(nc4.nc_get_var_text(ncid,varid, op));}

    @Override
    public synchronized
    int nc_get_var_schar(int ncid, int varid, byte[] ip)
    {ce(); return cx(nc4.nc_get_var_schar(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_ubyte(int ncid, int varid,  byte[] ip)
    {ce(); return cx(nc4.nc_get_var_ubyte(ncid,varid,  ip));}

    @Override
    public synchronized
    int nc_get_var_short(int ncid, int varid, short[] ip)
    {ce(); return cx(nc4.nc_get_var_short(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_ushort(int ncid, int varid, short[] ip)
    {ce(); return cx(nc4.nc_get_var_ushort(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_int(int ncid, int varid, int[] ip)
    {ce(); return cx(nc4.nc_get_var_int(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_uint(int ncid, int varid, int[] ip)
    {ce(); return cx(nc4.nc_get_var_uint(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_longlong(int ncid, int varid, long[] ip)
    {ce(); return cx(nc4.nc_get_var_longlong(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_ulonglong(int ncid, int varid, long[] ip)
    {ce(); return cx(nc4.nc_get_var_ulonglong(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_float(int ncid, int varid, float[] ip)
    {ce(); return cx(nc4.nc_get_var_float(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_double(int ncid, int varid, double[] ip)
    {ce(); return cx(nc4.nc_get_var_double(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_get_var_string(int ncid, int varid, String[] sarray)
    {ce(); return cx(nc4.nc_get_var_string(ncid,varid, sarray));}

    @Override
    public synchronized
    int nc_get_var1(int ncid, int varid, SizeT[] indexp, byte[] buf)
    {ce(); return cx(nc4.nc_get_var1(ncid,varid, indexp, buf));}

    @Override
    public synchronized
    int nc_get_var1_text(int ncid, int varid, SizeT[] indexp, byte[] op)
    {ce(); return cx(nc4.nc_get_var1_text(ncid,varid, indexp, op));}

    @Override
    public synchronized
    int nc_get_var1_schar(int ncid, int varid, SizeT[] indexp, byte[] ip)
    {ce(); return cx(nc4.nc_get_var1_schar(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_ubyte(int ncid, int varid, SizeT[] indexp, byte[] ip)
    {ce(); return cx(nc4.nc_get_var1_ubyte(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_short(int ncid, int varid, SizeT[] indexp, short[] ip)
    {ce(); return cx(nc4.nc_get_var1_short(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_ushort(int ncid, int varid, SizeT[] indexp, short[] ip)
    {ce(); return cx(nc4.nc_get_var1_ushort(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_int(int ncid, int varid, SizeT[] indexp, int[] ip)
    {ce(); return cx(nc4.nc_get_var1_int(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_uint(int ncid, int varid, SizeT[] indexp, int[] ip)
    {ce(); return cx(nc4.nc_get_var1_uint(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_longlong(int ncid, int varid, SizeT[] indexp, long[] ip)
    {ce(); return cx(nc4.nc_get_var1_longlong(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_ulonglong(int ncid, int varid, SizeT[] indexp, long[] ip)
    {ce(); return cx(nc4.nc_get_var1_ulonglong(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_float(int ncid, int varid, SizeT[] indexp, float[] ip)
    {ce(); return cx(nc4.nc_get_var1_float(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_double(int ncid, int varid, SizeT[] indexp, double[] ip)
    {ce(); return cx(nc4.nc_get_var1_double(ncid,varid, indexp, ip));}

    @Override
    public synchronized
    int nc_get_var1_string(int ncid, int varid, SizeT[] indexp, String[] sarray)
    {ce(); return cx(nc4.nc_get_var1_string(ncid,varid, indexp, sarray));}

    @Override
    public synchronized
    int nc_get_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] buf)
    {ce(); return cx(nc4.nc_get_vara(ncid,varid, startp, countp, buf));}

    @Override
    public synchronized
    int nc_get_vara_uchar(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_get_vara_uchar(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_schar(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_get_vara_schar(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_text(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_get_vara_text(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_short(int ncid, int varid, SizeT[] startp, SizeT[] countp, short[] ip)
    {ce(); return cx(nc4.nc_get_vara_short(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_ushort(int ncid, int varid, SizeT[] startp, SizeT[] countp, short[] ip)
    {ce(); return cx(nc4.nc_get_vara_ushort(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_int(int ncid, int varid, SizeT[] startp, SizeT[] countp, int[] ip)
    {ce(); return cx(nc4.nc_get_vara_int(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_uint(int ncid, int varid, SizeT[] startp, SizeT[] countp, int[] ip)
    {ce(); return cx(nc4.nc_get_vara_uint(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_longlong(int ncid, int varid, SizeT[] startp, SizeT[] countp, long[] ip)
    {ce(); return cx(nc4.nc_get_vara_longlong(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_ulonglong(int ncid, int varid, SizeT[] startp, SizeT[] countp, long[] ip)
    {ce(); return cx(nc4.nc_get_vara_ulonglong(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_float(int ncid, int varid, SizeT[] startp, SizeT[] countp, float[] ip)
    {ce(); return cx(nc4.nc_get_vara_float(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_double(int ncid, int varid, SizeT[] startp, SizeT[] countp, double[] ip)
    {ce(); return cx(nc4.nc_get_vara_double(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vara_string(int ncid, int varid, SizeT[] startp, SizeT[] countp, String[] ip)
    {ce(); return cx(nc4.nc_get_vara_string(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_get_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] buf)
    {ce(); return cx(nc4.nc_get_vars(ncid,varid, startp, countp, stridep, buf));}

    @Override
    public synchronized
    int nc_get_vars_uchar(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_get_vars_uchar(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_schar(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_get_vars_schar(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_text(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_get_vars_text(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_short(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, short[] ip)
    {ce(); return cx(nc4.nc_get_vars_short(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_ushort(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, short[] ip)
    {ce(); return cx(nc4.nc_get_vars_ushort(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_int(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, int[] ip)
    {ce(); return cx(nc4.nc_get_vars_int(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_uint(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, int[] ip)
    {ce(); return cx(nc4.nc_get_vars_uint(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_longlong(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, long[] ip)
    {ce(); return cx(nc4.nc_get_vars_longlong(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_ulonglong(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, long[] ip)
    {ce(); return cx(nc4.nc_get_vars_ulonglong(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_float(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, float[] ip)
    {ce(); return cx(nc4.nc_get_vars_float(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_double(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, double[] ip)
    {ce(); return cx(nc4.nc_get_vars_double(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_get_vars_string(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, String[] ip)
    {ce(); return cx(nc4.nc_get_vars_string(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_set_default_format(int format, IntByReference old_formatp)
    {ce(); return cx(nc4.nc_set_default_format(format, old_formatp));}

    @Override
    public synchronized
    int nc_create(String path, int cmode, IntByReference ncidp)
    {ce(); return cx(nc4.nc_create(path,cmode, ncidp));}

    @Override
    public synchronized
    int nc_enddef   (int ncid)
    {ce(); return cx(nc4.nc_enddef   (ncid));}

    @Override
    public synchronized
    int nc_sync     (int ncid)
    {ce(); return cx(nc4.nc_sync     (ncid));}

    @Override
    public synchronized
    int nc_def_grp (int parent_ncid, String name, IntByReference new_ncid)
    {ce(); return cx(nc4.nc_def_grp (parent_ncid,name, new_ncid));}

    @Override
    public synchronized
    int nc_def_dim(int ncid,  String name, SizeT len, IntByReference dimid)
    {ce(); return cx(nc4.nc_def_dim(ncid,name,len, dimid));}

    @Override
    public synchronized
    int nc_inq_dimlen(int ncid, int dimid, SizeTByReference lenp)
    {ce(); return cx(nc4.nc_inq_dimlen(ncid,dimid, lenp));}

    @Override
    public synchronized
    int nc_def_var (int ncid, String name, SizeT xtype, int ndims, int[] dimids, IntByReference varidp)
    {ce(); return cx(nc4.nc_def_var (ncid,name,xtype,ndims, dimids, varidp));}

    @Override
    public synchronized
    int nc_def_compound(int ncid, SizeT size, String name, IntByReference typeidp)
    {ce(); return cx(nc4.nc_def_compound(ncid,size,name, typeidp));}

    @Override
    public synchronized
    int nc_insert_compound(int ncid, int typeid, String name, SizeT offset, int field_typeid)
    {ce(); return cx(nc4.nc_insert_compound(ncid,typeid,name,offset,field_typeid));}

    @Override
    public synchronized
    int nc_insert_array_compound(int ncid, int typeid, String name, SizeT offset, int field_typeid, int ndims, int[] dim_sizes)
    {ce(); return cx(nc4.nc_insert_array_compound(ncid,typeid,name,offset,field_typeid,ndims, dim_sizes));}

    @Override
    public synchronized
    int nc_def_enum(int ncid, int base_typeid, String name, IntByReference typeidp)
    {ce(); return cx(nc4.nc_def_enum(ncid,base_typeid,name, typeidp));}

    @Override
    public synchronized
    int nc_insert_enum(int ncid, int enumid, String name, IntByReference value)
    {ce(); return cx(nc4.nc_insert_enum(ncid,enumid,name, value));}

    @Override
    public synchronized
    int nc_rename_grp(int grpid, String name)
    {ce(); return cx(nc4.nc_rename_grp(grpid,name));}

    @Override
    public synchronized
    int nc_put_var(int ncid, int varid, byte[] bbuff)
    {ce(); return cx(nc4.nc_put_var(ncid,varid, bbuff));}

    @Override
    public synchronized
    int nc_put_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] bbuff)
    {ce(); return cx(nc4.nc_put_vara(ncid,varid, startp, countp, bbuff));}

    @Override
    public synchronized
    int nc_put_vara_uchar(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_put_vara_uchar(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_schar(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_put_vara_schar(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_text(int ncid, int varid, SizeT[] startp, SizeT[] countp, byte[] ip)
    {ce(); return cx(nc4.nc_put_vara_text(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_short(int ncid, int varid, SizeT[] startp, SizeT[] countp, short[] ip)
    {ce(); return cx(nc4.nc_put_vara_short(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_ushort(int ncid, int varid, SizeT[] startp, SizeT[] countp, short[] ip)
    {ce(); return cx(nc4.nc_put_vara_ushort(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_int(int ncid, int varid, SizeT[] startp, SizeT[] countp, int[] ip)
    {ce(); return cx(nc4.nc_put_vara_int(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_uint(int ncid, int varid, SizeT[] startp, SizeT[] countp, int[] ip)
    {ce(); return cx(nc4.nc_put_vara_uint(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_longlong(int ncid, int varid, SizeT[] startp, SizeT[] countp, long[] ip)
    {ce(); return cx(nc4.nc_put_vara_longlong(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_ulonglong(int ncid, int varid, SizeT[] startp, SizeT[] countp, long[] ip)
    {ce(); return cx(nc4.nc_put_vara_ulonglong(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_float(int ncid, int varid, SizeT[] startp, SizeT[] countp, float[] ip)
    {ce(); return cx(nc4.nc_put_vara_float(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_double(int ncid, int varid, SizeT[] startp, SizeT[] countp, double[] ip)
    {ce(); return cx(nc4.nc_put_vara_double(ncid,varid, startp, countp, ip));}

    @Override
    public synchronized
    int nc_put_vara_string(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, String[] ip)
    {ce(); return cx(nc4.nc_put_vara_string(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[]  bbuff)
    {ce(); return cx(nc4.nc_put_vars(ncid,varid, startp, countp, stridep, bbuff));}

    @Override
    public synchronized
    int nc_put_vars_uchar(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_put_vars_uchar(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_schar(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_put_vars_schar(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_text(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, byte[] ip)
    {ce(); return cx(nc4.nc_put_vars_text(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_short(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, short[] ip)
    {ce(); return cx(nc4.nc_put_vars_short(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_ushort(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, short[] ip)
    {ce(); return cx(nc4.nc_put_vars_ushort(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_int(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, int[] ip)
    {ce(); return cx(nc4.nc_put_vars_int(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_uint(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, int[] ip)
    {ce(); return cx(nc4.nc_put_vars_uint(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_longlong(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, long[] ip)
    {ce(); return cx(nc4.nc_put_vars_longlong(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_ulonglong(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, long[] ip)
    {ce(); return cx(nc4.nc_put_vars_ulonglong(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_float(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, float[] ip)
    {ce(); return cx(nc4.nc_put_vars_float(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_double(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, double[] ip)
    {ce(); return cx(nc4.nc_put_vars_double(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_vars_string(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, String[] ip)
    {ce(); return cx(nc4.nc_put_vars_string(ncid,varid, startp, countp, stridep, ip));}

    @Override
    public synchronized
    int nc_put_var_uchar(int ncid, int varid, byte[] ip)
    {ce(); return cx(nc4.nc_put_var_uchar(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_schar(int ncid, int varid,  byte[] ip)
    {ce(); return cx(nc4.nc_put_var_schar(ncid,varid,  ip));}

    @Override
    public synchronized
    int nc_put_var_text(int ncid, int varid, byte[] ip)
    {ce(); return cx(nc4.nc_put_var_text(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_short(int ncid, int varid, short[] ip)
    {ce(); return cx(nc4.nc_put_var_short(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_ushort(int ncid, int varid, short[] ip)
    {ce(); return cx(nc4.nc_put_var_ushort(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_int(int ncid, int varid, int[] ip)
    {ce(); return cx(nc4.nc_put_var_int(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_uint(int ncid, int varid, int[] ip)
    {ce(); return cx(nc4.nc_put_var_uint(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_longlong(int ncid, int varid, long[] ip)
    {ce(); return cx(nc4.nc_put_var_longlong(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_ulonglong(int ncid, int varid, long[] ip)
    {ce(); return cx(nc4.nc_put_var_ulonglong(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_float(int ncid, int varid, float[] ip)
    {ce(); return cx(nc4.nc_put_var_float(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_double(int ncid, int varid, double[] ip)
    {ce(); return cx(nc4.nc_put_var_double(ncid,varid, ip));}

    @Override
    public synchronized
    int nc_put_var_string(int ncid, int varid, String[] op)
    {ce(); return cx(nc4.nc_put_var_string(ncid,varid, op));}

    @Override
    public synchronized
    int nc_put_att (int ncid, int varid, String name, int xtype, SizeT len, byte[] value)
    {ce(); return cx(nc4.nc_put_att (ncid,varid,name,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_string(int ncid, int varid, String attName, SizeT len, String[] value)
    {ce(); return cx(nc4.nc_put_att_string(ncid,varid,attName,len, value));}

    @Override
    public synchronized
    int nc_put_att_text(int ncid, int varid, String attName, SizeT len, byte[] value)
    {ce(); return cx(nc4.nc_put_att_text(ncid,varid,attName,len, value));}

    @Override
    public synchronized
    int nc_put_att_uchar(int ncid, int varid, String attName, int xtype, SizeT len, byte[] value)
    {ce(); return cx(nc4.nc_put_att_uchar(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_schar(int ncid, int varid, String attName, int xtype, SizeT len, byte[] value)
    {ce(); return cx(nc4.nc_put_att_schar(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_short(int ncid, int varid, String attName, int xtype, SizeT len, short[] value)
    {ce(); return cx(nc4.nc_put_att_short(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_ushort(int ncid, int varid, String attName, int xtype, SizeT len, short[] value)
    {ce(); return cx(nc4.nc_put_att_ushort(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_int(int ncid, int varid, String attName, int xtype, SizeT len, int[] value)
    {ce(); return cx(nc4.nc_put_att_int(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_uint(int ncid, int varid, String attName, int xtype, SizeT len, int[] value)
    {ce(); return cx(nc4.nc_put_att_uint(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_longlong(int ncid, int varid, String attName, int xtype, SizeT len, long[] value)
    {ce(); return cx(nc4.nc_put_att_longlong(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_ulonglong(int ncid, int varid, String attName, int xtype, SizeT len, long[] value)
    {ce(); return cx(nc4.nc_put_att_ulonglong(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_float(int ncid, int varid, String attName, int xtype, SizeT len, float[] value)
    {ce(); return cx(nc4.nc_put_att_float(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_att_double(int ncid, int varid, String attName, int xtype, SizeT len, double[] value)
    {ce(); return cx(nc4.nc_put_att_double(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_def_var_deflate(int ncid, int varid, int shuffle, int deflate, int deflate_level)
    {ce(); return cx(nc4.nc_def_var_deflate(ncid,varid,shuffle,deflate,deflate_level));}

    @Override
    public synchronized
    int nc_inq_var_deflate(int ncid, int varid, IntByReference shufflep, IntByReference deflatep, IntByReference deflate_levelp)
    {ce(); return cx(nc4.nc_inq_var_deflate(ncid,varid, shufflep, deflatep, deflate_levelp));}

    @Override
    public synchronized
    int nc_inq_var_szip(int ncid, int varid, IntByReference options_maskp, IntByReference pixels_per_blockp)
    {ce(); return cx(nc4.nc_inq_var_szip(ncid,varid, options_maskp, pixels_per_blockp));}

    @Override
    public synchronized
    int nc_def_var_fletcher32(int ncid, int varid, int fletcher32)
    {ce(); return cx(nc4.nc_def_var_fletcher32(ncid,varid,fletcher32));}

    @Override
    public synchronized
    int nc_inq_var_fletcher32(int ncid, int varid, IntByReference fletcher32p)
    {ce(); return cx(nc4.nc_inq_var_fletcher32(ncid,varid, fletcher32p));}

    @Override
    public synchronized
    int nc_def_var_chunking(int ncid, int varid, int storage, SizeT[] chunksizesp)
    {ce(); return cx(nc4.nc_def_var_chunking(ncid,varid,storage, chunksizesp));}

    @Override
    public synchronized
    int nc_inq_var_chunking(int ncid, int varid, IntByReference storagep, SizeT[] chunksizesp)
    {ce(); return cx(nc4.nc_inq_var_chunking(ncid,varid, storagep, chunksizesp));}

    @Override
    public synchronized
    int nc_def_var_fill(int ncid, int varid, int no_fill, byte[] fill_value)
    {ce(); return cx(nc4.nc_def_var_fill(ncid,varid,no_fill, fill_value));}

    @Override
    public synchronized
    int nc_inq_var_fill(int ncid, int varid, IntByReference no_fill, byte[] fill_valuep)
    {ce(); return cx(nc4.nc_inq_var_fill(ncid,varid, no_fill, fill_valuep));}

    @Override
    public synchronized
    int nc_def_var_endian(int ncid, int varid, int endian)
    {ce(); return cx(nc4.nc_def_var_endian(ncid,varid,endian));}

    @Override
    public synchronized
    int nc_inq_var_endian(int ncid, int varid, IntByReference endianp)
    {ce(); return cx(nc4.nc_inq_var_endian(ncid,varid, endianp));}

    @Override
    public synchronized
    int nc_set_fill(int ncid, int fillmode, IntByReference old_modep)
    {ce(); return cx(nc4.nc_set_fill(ncid,fillmode, old_modep));}

    @Override
    public synchronized
    int nc_set_chunk_cache(SizeT size, SizeT nelems, float preemption)
    {ce(); return cx(nc4.nc_set_chunk_cache(size,nelems,preemption));}

    @Override
    public synchronized
    int nc_get_chunk_cache(SizeTByReference sizep, SizeTByReference nelemsp, FloatByReference preemptionp)
    {ce(); return cx(nc4.nc_get_chunk_cache(sizep, nelemsp, preemptionp));}

    @Override
    public synchronized
    int nc_set_var_chunk_cache(int ncid, int varid, SizeT size, SizeT nelems, float preemption)
    {ce(); return cx(nc4.nc_set_var_chunk_cache(ncid,varid,size,nelems,preemption));}

    @Override
    public synchronized
    int nc_get_var_chunk_cache(int ncid, int varid, SizeTByReference sizep, SizeTByReference nelemsp, FloatByReference preemptionp)
    {ce(); return cx(nc4.nc_get_var_chunk_cache(ncid,varid, sizep, nelemsp, preemptionp));}

    @Override
    public synchronized
    int nc_set_log_level(int newlevel)
    {ce(); return cx(nc4.nc_set_log_level(newlevel));}

    @Override
    public synchronized
    int nc_inq_compound(int ncid, int xtype, byte[] name, SizeTByReference sizep, SizeTByReference nfieldsp)
    {ce(); return cx(nc4.nc_inq_compound(ncid,xtype, name, sizep, nfieldsp));}

    @Override
    public synchronized
    int nc_inq_compound_field(int ncid, int xtype, int fieldid, byte[] name, SizeTByReference offsetp, IntByReference field_typeidp, IntByReference ndimsp, int[] dims)
    {ce(); return cx(nc4.nc_inq_compound_field(ncid,xtype,fieldid, name, offsetp, field_typeidp, ndimsp, dims));}

    @Override
    public synchronized
    int nc_inq_vlen(int ncid, int xtype, byte[] name, SizeTByReference datum_sizep, IntByReference base_nc_typep)
    {ce(); return cx(nc4.nc_inq_vlen(ncid,xtype, name, datum_sizep, base_nc_typep));}

    @Override
    public synchronized
    int nc_get_att(int ncid, int varid, String name, Vlen_t[] vlen)
    {ce(); return cx(nc4.nc_get_att(ncid,varid,name, vlen));}

    @Override
    public synchronized
    int nc_get_var(int ncid, int varid, Vlen_t[] vlen)
    {ce(); return cx(nc4.nc_get_var(ncid,varid, vlen));}

    @Override
    public synchronized
    int nc_get_var1(int ncid, int varid, SizeT[] indexp, Vlen_t[] vlen)
    {ce(); return cx(nc4.nc_get_var1(ncid,varid, indexp, vlen));}

    @Override
    public synchronized
    int nc_get_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, Vlen_t[] v)
    {ce(); return cx(nc4.nc_get_vara(ncid,varid, startp, countp, v));}

    @Override
    public synchronized
    int nc_get_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, Vlen_t[] v)
    {ce(); return cx(nc4.nc_get_vars(ncid,varid, startp, countp, stridep, v));}

    @Override
    public synchronized
    int nc_put_att(int ncid, int varid, String attName, int xtype, SizeT len, Vlen_t[] value)
    {ce(); return cx(nc4.nc_put_att(ncid,varid,attName,xtype,len, value));}

    @Override
    public synchronized
    int nc_put_var(int ncid, int varid, Vlen_t[] vlen)
    {ce(); return cx(nc4.nc_put_var(ncid,varid, vlen));}

    @Override
    public synchronized
    int nc_put_var1(int ncid, int varid, SizeT[] indexp, Vlen_t[] vlen)
    {ce(); return cx(nc4.nc_put_var1(ncid,varid, indexp, vlen));}

    @Override
    public synchronized
    int nc_put_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, Vlen_t[] v)
    {ce(); return cx(nc4.nc_put_vara(ncid,varid, startp, countp, v));}

    @Override
    public synchronized
    int nc_put_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, Vlen_t[] v)
    {ce(); return cx(nc4.nc_put_vars(ncid,varid, startp, countp, stridep, v));}

    @Override
    public synchronized
    int nc_get_att(int ncid, int varid, String name, Pointer p)
    {ce(); return cx(nc4.nc_get_att(ncid,varid,name,p));}

    @Override
    public synchronized
    int nc_get_var(int ncid, int varid, Pointer p)
    {ce(); return cx(nc4.nc_get_var(ncid,varid,p));}

    @Override
    public synchronized
    int nc_get_var1(int ncid, int varid, SizeT[] indexp, Pointer p)
    {ce(); return cx(nc4.nc_get_var1(ncid,varid, indexp,p));}

    @Override
    public synchronized
    int nc_get_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, Pointer p)
    {ce(); return cx(nc4.nc_get_vara(ncid,varid, startp, countp,p));}

    @Override
    public synchronized
    int nc_get_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, Pointer p)
    {ce(); return cx(nc4.nc_get_vars(ncid,varid, startp, countp, stridep,p));}

    @Override
    public synchronized
    int nc_put_att(int ncid, int varid, String attName, int xtype, SizeT len, Pointer p)
    {ce(); return cx(nc4.nc_put_att(ncid,varid,attName,xtype,len,p));}

    @Override
    public synchronized
    int nc_put_var(int ncid, int varid, Pointer p)
    {ce(); return cx(nc4.nc_put_var(ncid,varid,p));}

    @Override
    public synchronized
    int nc_put_var1(int ncid, int varid, SizeT[] indexp, Pointer p)
    {ce(); return cx(nc4.nc_put_var1(ncid,varid, indexp,p));}

    @Override
    public synchronized
    int nc_put_vara(int ncid, int varid, SizeT[] startp, SizeT[] countp, Pointer p)
    {ce(); return cx(nc4.nc_put_vara(ncid,varid, startp, countp,p));}

    @Override
    public synchronized
    int nc_put_vars(int ncid, int varid, SizeT[] startp, SizeT[] countp, SizeT[] stridep, Pointer p)
    {ce(); return cx(nc4.nc_put_vars(ncid,varid, startp, countp, stridep,p));}

}
