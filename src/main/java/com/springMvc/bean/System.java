package com.springMvc.bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "WM_SYSTEM")  
public class System {
	@GeneratedValue(generator = "system-uuid")  
	@GenericGenerator(name = "system-uuid", strategy = "uuid")  
	@Id
	private String sid;
    private String c_code;
    private String c_name;
    private String c_caption;
    private String p_type;
    private String s_type;
    private String p_sys_type;
    private String s_sys_type;
    private String p_extension;
    private String s_extension;
    private Date t_begin;
    private String p_deploy;
    private String s_deploy;
    private String p_run;
    private String s_run;
    private String c_bind;
    private String p_net;
    private String s_net;
    private String c_safe;
    private String c_url;
    private String s_org_01;
    private String r_user_01;
    private String z_user_01;
    private String s_user_01;
    private String s_tel_01	;
    private String b_sms_01	;
    private String s_org_02	;
    private String r_user_02;
    private String z_user_02;
    private String s_user_02;
    private String s_tel_02	;
    private String b_sms_02	;
    private String s_org_03	;
    private String r_user_03;
    private String z_user_03;
    private String s_user_03;
    private String s_tel_03	;
    private String b_sms_03	;
    private String s_org_04	;
    private String r_user_04;
    private String z_user_04;
    private String s_user_04;
    private String s_tel_04	;
    private String b_sms_04	;
    private Date t_sync	;
    private String c_sys_desc;
    private String c_remark;
    private String c_version;
    private String z_source	;
    private String r_create	;
    private String s_create	;
    private Date t_create	;
    private String r_update	;
    private String s_update	;
    private Date t_update	;
    private String c_update_host;
    private String c_update_agent;
    private String c_update_client;
    private String c_update_version;
    private String n_index;
    private String b_delete;
    private String b_emphasis;
    private String c_sbbh;
    private String p_sbfl;
    private String s_sbfl;
    private String p_rjxl;
    private String s_rjxl;
    private String c_bzmc;
    private String c_bzmcdmc;
    private String p_dqzt;
    private String s_dqzt;
    private String c_cqgs;
    private String n_zcyz;
    private String c_cghtbh;
    private String c_fwhtbh;
    private String s_org_05;
    private String s_user_05;
    private String r_user_05;
    private String z_user_05;
    private String s_tel_05;
    private String p_fwjb;
    private String s_fwjb;
    private Date t_fwksrq;
    private Date t_fwdqrq;
    private String c_lybm;
    private String c_yxdw;
    private String c_yxbm;
    private Date t_aqpgrq;
    private Date t_aqjgpj;
    private String p_jkcl;
    private String s_jkcl;
    private String c_ip;
    private String c_port;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_caption() {
		return c_caption;
	}
	public void setC_caption(String c_caption) {
		this.c_caption = c_caption;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getS_type() {
		return s_type;
	}
	public void setS_type(String s_type) {
		this.s_type = s_type;
	}
	public String getP_sys_type() {
		return p_sys_type;
	}
	public void setP_sys_type(String p_sys_type) {
		this.p_sys_type = p_sys_type;
	}
	public String getS_sys_type() {
		return s_sys_type;
	}
	public void setS_sys_type(String s_sys_type) {
		this.s_sys_type = s_sys_type;
	}
	public String getP_extension() {
		return p_extension;
	}
	public void setP_extension(String p_extension) {
		this.p_extension = p_extension;
	}
	public String getS_extension() {
		return s_extension;
	}
	public void setS_extension(String s_extension) {
		this.s_extension = s_extension;
	}
	public Date getT_begin() {
		return t_begin;
	}
	public void setT_begin(Date t_begin) {
		this.t_begin = t_begin;
	}
	public String getP_deploy() {
		return p_deploy;
	}
	public void setP_deploy(String p_deploy) {
		this.p_deploy = p_deploy;
	}
	public String getS_deploy() {
		return s_deploy;
	}
	public void setS_deploy(String s_deploy) {
		this.s_deploy = s_deploy;
	}
	public String getP_run() {
		return p_run;
	}
	public void setP_run(String p_run) {
		this.p_run = p_run;
	}
	public String getS_run() {
		return s_run;
	}
	public void setS_run(String s_run) {
		this.s_run = s_run;
	}
	public String getC_bind() {
		return c_bind;
	}
	public void setC_bind(String c_bind) {
		this.c_bind = c_bind;
	}
	public String getP_net() {
		return p_net;
	}
	public void setP_net(String p_net) {
		this.p_net = p_net;
	}
	public String getS_net() {
		return s_net;
	}
	public void setS_net(String s_net) {
		this.s_net = s_net;
	}
	public String getC_safe() {
		return c_safe;
	}
	public void setC_safe(String c_safe) {
		this.c_safe = c_safe;
	}
	public String getC_url() {
		return c_url;
	}
	public void setC_url(String c_url) {
		this.c_url = c_url;
	}
	public String getS_org_01() {
		return s_org_01;
	}
	public void setS_org_01(String s_org_01) {
		this.s_org_01 = s_org_01;
	}
	public String getR_user_01() {
		return r_user_01;
	}
	public void setR_user_01(String r_user_01) {
		this.r_user_01 = r_user_01;
	}
	public String getZ_user_01() {
		return z_user_01;
	}
	public void setZ_user_01(String z_user_01) {
		this.z_user_01 = z_user_01;
	}
	public String getS_user_01() {
		return s_user_01;
	}
	public void setS_user_01(String s_user_01) {
		this.s_user_01 = s_user_01;
	}
	public String getS_tel_01() {
		return s_tel_01;
	}
	public void setS_tel_01(String s_tel_01) {
		this.s_tel_01 = s_tel_01;
	}
	public String getB_sms_01() {
		return b_sms_01;
	}
	public void setB_sms_01(String b_sms_01) {
		this.b_sms_01 = b_sms_01;
	}
	public String getS_org_02() {
		return s_org_02;
	}
	public void setS_org_02(String s_org_02) {
		this.s_org_02 = s_org_02;
	}
	public String getR_user_02() {
		return r_user_02;
	}
	public void setR_user_02(String r_user_02) {
		this.r_user_02 = r_user_02;
	}
	public String getZ_user_02() {
		return z_user_02;
	}
	public void setZ_user_02(String z_user_02) {
		this.z_user_02 = z_user_02;
	}
	public String getS_user_02() {
		return s_user_02;
	}
	public void setS_user_02(String s_user_02) {
		this.s_user_02 = s_user_02;
	}
	public String getS_tel_02() {
		return s_tel_02;
	}
	public void setS_tel_02(String s_tel_02) {
		this.s_tel_02 = s_tel_02;
	}
	public String getB_sms_02() {
		return b_sms_02;
	}
	public void setB_sms_02(String b_sms_02) {
		this.b_sms_02 = b_sms_02;
	}
	public String getS_org_03() {
		return s_org_03;
	}
	public void setS_org_03(String s_org_03) {
		this.s_org_03 = s_org_03;
	}
	public String getR_user_03() {
		return r_user_03;
	}
	public void setR_user_03(String r_user_03) {
		this.r_user_03 = r_user_03;
	}
	public String getZ_user_03() {
		return z_user_03;
	}
	public void setZ_user_03(String z_user_03) {
		this.z_user_03 = z_user_03;
	}
	public String getS_user_03() {
		return s_user_03;
	}
	public void setS_user_03(String s_user_03) {
		this.s_user_03 = s_user_03;
	}
	public String getS_tel_03() {
		return s_tel_03;
	}
	public void setS_tel_03(String s_tel_03) {
		this.s_tel_03 = s_tel_03;
	}
	public String getB_sms_03() {
		return b_sms_03;
	}
	public void setB_sms_03(String b_sms_03) {
		this.b_sms_03 = b_sms_03;
	}
	public String getS_org_04() {
		return s_org_04;
	}
	public void setS_org_04(String s_org_04) {
		this.s_org_04 = s_org_04;
	}
	public String getR_user_04() {
		return r_user_04;
	}
	public void setR_user_04(String r_user_04) {
		this.r_user_04 = r_user_04;
	}
	public String getZ_user_04() {
		return z_user_04;
	}
	public void setZ_user_04(String z_user_04) {
		this.z_user_04 = z_user_04;
	}
	public String getS_user_04() {
		return s_user_04;
	}
	public void setS_user_04(String s_user_04) {
		this.s_user_04 = s_user_04;
	}
	public String getS_tel_04() {
		return s_tel_04;
	}
	public void setS_tel_04(String s_tel_04) {
		this.s_tel_04 = s_tel_04;
	}
	public String getB_sms_04() {
		return b_sms_04;
	}
	public void setB_sms_04(String b_sms_04) {
		this.b_sms_04 = b_sms_04;
	}
	public Date getT_sync() {
		return t_sync;
	}
	public void setT_sync(Date t_sync) {
		this.t_sync = t_sync;
	}
	public String getC_sys_desc() {
		return c_sys_desc;
	}
	public void setC_sys_desc(String c_sys_desc) {
		this.c_sys_desc = c_sys_desc;
	}
	public String getC_remark() {
		return c_remark;
	}
	public void setC_remark(String c_remark) {
		this.c_remark = c_remark;
	}
	public String getC_version() {
		return c_version;
	}
	public void setC_version(String c_version) {
		this.c_version = c_version;
	}
	public String getZ_source() {
		return z_source;
	}
	public void setZ_source(String z_source) {
		this.z_source = z_source;
	}
	public String getR_create() {
		return r_create;
	}
	public void setR_create(String r_create) {
		this.r_create = r_create;
	}
	public String getS_create() {
		return s_create;
	}
	public void setS_create(String s_create) {
		this.s_create = s_create;
	}
	public Date getT_create() {
		return t_create;
	}
	public void setT_create(Date t_create) {
		this.t_create = t_create;
	}
	public String getR_update() {
		return r_update;
	}
	public void setR_update(String r_update) {
		this.r_update = r_update;
	}
	public String getS_update() {
		return s_update;
	}
	public void setS_update(String s_update) {
		this.s_update = s_update;
	}
	public Date getT_update() {
		return t_update;
	}
	public void setT_update(Date t_update) {
		this.t_update = t_update;
	}
	public String getC_update_host() {
		return c_update_host;
	}
	public void setC_update_host(String c_update_host) {
		this.c_update_host = c_update_host;
	}
	public String getC_update_agent() {
		return c_update_agent;
	}
	public void setC_update_agent(String c_update_agent) {
		this.c_update_agent = c_update_agent;
	}
	public String getC_update_client() {
		return c_update_client;
	}
	public void setC_update_client(String c_update_client) {
		this.c_update_client = c_update_client;
	}
	public String getC_update_version() {
		return c_update_version;
	}
	public void setC_update_version(String c_update_version) {
		this.c_update_version = c_update_version;
	}
	public String getN_index() {
		return n_index;
	}
	public void setN_index(String n_index) {
		this.n_index = n_index;
	}
	public String getB_delete() {
		return b_delete;
	}
	public void setB_delete(String b_delete) {
		this.b_delete = b_delete;
	}
	public String getB_emphasis() {
		return b_emphasis;
	}
	public void setB_emphasis(String b_emphasis) {
		this.b_emphasis = b_emphasis;
	}
	public String getC_sbbh() {
		return c_sbbh;
	}
	public void setC_sbbh(String c_sbbh) {
		this.c_sbbh = c_sbbh;
	}
	public String getP_sbfl() {
		return p_sbfl;
	}
	public void setP_sbfl(String p_sbfl) {
		this.p_sbfl = p_sbfl;
	}
	public String getS_sbfl() {
		return s_sbfl;
	}
	public void setS_sbfl(String s_sbfl) {
		this.s_sbfl = s_sbfl;
	}
	public String getP_rjxl() {
		return p_rjxl;
	}
	public void setP_rjxl(String p_rjxl) {
		this.p_rjxl = p_rjxl;
	}
	public String getS_rjxl() {
		return s_rjxl;
	}
	public void setS_rjxl(String s_rjxl) {
		this.s_rjxl = s_rjxl;
	}
	public String getC_bzmc() {
		return c_bzmc;
	}
	public void setC_bzmc(String c_bzmc) {
		this.c_bzmc = c_bzmc;
	}
	public String getC_bzmcdmc() {
		return c_bzmcdmc;
	}
	public void setC_bzmcdmc(String c_bzmcdmc) {
		this.c_bzmcdmc = c_bzmcdmc;
	}
	public String getP_dqzt() {
		return p_dqzt;
	}
	public void setP_dqzt(String p_dqzt) {
		this.p_dqzt = p_dqzt;
	}
	public String getS_dqzt() {
		return s_dqzt;
	}
	public void setS_dqzt(String s_dqzt) {
		this.s_dqzt = s_dqzt;
	}
	public String getC_cqgs() {
		return c_cqgs;
	}
	public void setC_cqgs(String c_cqgs) {
		this.c_cqgs = c_cqgs;
	}
	public String getN_zcyz() {
		return n_zcyz;
	}
	public void setN_zcyz(String n_zcyz) {
		this.n_zcyz = n_zcyz;
	}
	public String getC_cghtbh() {
		return c_cghtbh;
	}
	public void setC_cghtbh(String c_cghtbh) {
		this.c_cghtbh = c_cghtbh;
	}
	public String getC_fwhtbh() {
		return c_fwhtbh;
	}
	public void setC_fwhtbh(String c_fwhtbh) {
		this.c_fwhtbh = c_fwhtbh;
	}
	public String getS_org_05() {
		return s_org_05;
	}
	public void setS_org_05(String s_org_05) {
		this.s_org_05 = s_org_05;
	}
	public String getS_user_05() {
		return s_user_05;
	}
	public void setS_user_05(String s_user_05) {
		this.s_user_05 = s_user_05;
	}
	public String getR_user_05() {
		return r_user_05;
	}
	public void setR_user_05(String r_user_05) {
		this.r_user_05 = r_user_05;
	}
	public String getZ_user_05() {
		return z_user_05;
	}
	public void setZ_user_05(String z_user_05) {
		this.z_user_05 = z_user_05;
	}
	public String getS_tel_05() {
		return s_tel_05;
	}
	public void setS_tel_05(String s_tel_05) {
		this.s_tel_05 = s_tel_05;
	}
	public String getP_fwjb() {
		return p_fwjb;
	}
	public void setP_fwjb(String p_fwjb) {
		this.p_fwjb = p_fwjb;
	}
	public String getS_fwjb() {
		return s_fwjb;
	}
	public void setS_fwjb(String s_fwjb) {
		this.s_fwjb = s_fwjb;
	}
	public Date getT_fwksrq() {
		return t_fwksrq;
	}
	public void setT_fwksrq(Date t_fwksrq) {
		this.t_fwksrq = t_fwksrq;
	}
	public Date getT_fwdqrq() {
		return t_fwdqrq;
	}
	public void setT_fwdqrq(Date t_fwdqrq) {
		this.t_fwdqrq = t_fwdqrq;
	}
	public String getC_lybm() {
		return c_lybm;
	}
	public void setC_lybm(String c_lybm) {
		this.c_lybm = c_lybm;
	}
	public String getC_yxdw() {
		return c_yxdw;
	}
	public void setC_yxdw(String c_yxdw) {
		this.c_yxdw = c_yxdw;
	}
	public String getC_yxbm() {
		return c_yxbm;
	}
	public void setC_yxbm(String c_yxbm) {
		this.c_yxbm = c_yxbm;
	}
	public Date getT_aqpgrq() {
		return t_aqpgrq;
	}
	public void setT_aqpgrq(Date t_aqpgrq) {
		this.t_aqpgrq = t_aqpgrq;
	}
	public Date getT_aqjgpj() {
		return t_aqjgpj;
	}
	public void setT_aqjgpj(Date t_aqjgpj) {
		this.t_aqjgpj = t_aqjgpj;
	}
	public String getP_jkcl() {
		return p_jkcl;
	}
	public void setP_jkcl(String p_jkcl) {
		this.p_jkcl = p_jkcl;
	}
	public String getS_jkcl() {
		return s_jkcl;
	}
	public void setS_jkcl(String s_jkcl) {
		this.s_jkcl = s_jkcl;
	}
	public String getC_ip() {
		return c_ip;
	}
	public void setC_ip(String c_ip) {
		this.c_ip = c_ip;
	}
	public String getC_port() {
		return c_port;
	}
	public void setC_port(String c_port) {
		this.c_port = c_port;
	}
    
    
}
