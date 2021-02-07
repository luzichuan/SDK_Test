package com.example.sdk_test.com.tencentcs.iotvideo;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.sdk_test.R;
import com.tencentcs.iotvideo.IoTVideoError;
import com.tencentcs.iotvideo.IoTVideoSdk;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IoTVideoError_Test {

    public static final int ERROR_NONE = 0;
    public static final int AC_frm_type_err = 1;
    public static final int AC_frm_len_err = 2;
    public static final int AC_frm_bson_hashval_err = 3;
    public static final int AC_GdmType_err = 4;
    public static final int AC_UploadReq_termid_is_not_dev_err = 5;
    public static final int AC_MsgBody_GdmBsonDat_Leaf_length_err = 6;
    public static final int AC_MsgBody_GdmJsonDat_Leaf_length_err = 7;
    public static final int AC_MsgBody_GetGdmDefBson_err = 8;
    public static final int AC_MsgBody_GdmJsonDat_length_err = 9;
    public static final int AC_MsgBody_GdmDat_Leaf_path_err = 10;
    public static final int AC_MsgBody_GdmDat_content_err = 11;
    public static final int AC_csrv_no_term_GdmDat_err = 12;
    public static final int AC_csrv_no_term_err = 13;
    public static final int AC_csrv_no_term_productID_err = 14;
    public static final int AC_TermOnlineReq_olinf_parm_err = 20;
    public static final int AC_TermOnlineReq_opt_with_fp_but_no_with_termid = 21;
    public static final int AC_Dat_UploadReq_dat_type_json_but_no_opt_with_termid_err = 31;
    public static final int AC_Dat_UploadReq_dat_type_err = 32;
    public static final int AC_other_err = 100;
    public static final int AC_centerInner_load_bson_err = 101;
    public static final int AC_centerInner_load_json_err = 102;
    public static final int AC_centerInner_get_bson_raw_err = 103;
    public static final int AC_centerInner_insert_user_fail = 110;
    public static final int AC_centerInner_insert_dev_fail = 111;
    public static final int AC_centerInner_find_login_user_err = 112;
    public static final int AC_centerInner_login_user_utcinitchgd_lower_err = 113;
    public static final int AC_centerInner_login_dev_utcinitchgd_lower_err = 114;
    public static final int AC_centerInner_processDevLastWord_err = 120;
    public static final int AC_MsgBody_LastWords_topic_is_not_valide_err = 121;
    public static final int AC_MsgBody_LastWords_json_is_not_valide_err = 122;
    public static final int AC_MsgBody_LastWords_not_with_livetime_err = 123;
    public static final int AC_MsgBody_LastWords_not_with_topic_err = 124;
    public static final int AC_MsgBody_LastWords_not_with_json_err = 125;
    public static final int AC_MsgBody_LastWords_action_is_err = 126;
    public static final int AC_MsgBody_LastWords_query_is_none = 127;
    public static final int ASrv_centerInner_other_err = 200;
    public static final int ASrv_TempSubscription_termid_is_not_usr_err = 300;
    public static final int ASrv_RdbTermListReq_neither_get_online_nor_get_offline_err = 400;
    public static final int ASrv_AllTermInitReq_other_err = 500;
    public static final int ASrv_dst_offline = 8000;
    public static final int ASrv_dst_notfound_asrv = 8001;
    public static final int ASrv_dst_notexsit = 8002;
    public static final int ASrv_dst_error_relation = 8003;
    public static final int ASrv_data_chkfrm_fail = 8004;
    public static final int ASrv_data_loadjson_fail = 8005;
    public static final int ASrv_data_modifytick_fail = 8006;
    public static final int ASrv_tocsrv_timeout = 8007;
    public static final int ASrv_url_parse_fail = 8008;
    public static final int ASrv_csrv_reply_err = 8009;
    public static final int ASrv_forward_toASrv_timeout = 8010;
    public static final int ASrv_forward_toASrv_fail = 8011;
    public static final int ASrv_forward_toTerm_timeout = 8012;
    public static final int ASrv_forward_toTerm_fail = 8013;
    public static final int ASrv_handle_fail = 8014;
    public static final int ASrv_dstid_parse_faild = 8015;
    public static final int ASrv_dstid_isuser = 8016;
    public static final int ASrv_calc_leaf_fail = 8017;
    public static final int ASrv_set_timeval_leafval_fail = 8018;
    public static final int ASrv_calc_forward_json_fail = 8019;
    public static final int ASrv_tmpsubs_parse_fail = 8020;
    public static final int ASrv_csrvctrl_trgtype_error = 8021;
    public static final int ASrv_binderror_dev_usr_has_bind = 8022;
    public static final int ASrv_binderror_dev_has_bind_other = 8023;
    public static final int ASrv_binderror_customer_diffrent = 8024;
    public static final int ASrv_unformat_jsstr_fail = 8025;
    public static final int ASrv_netcfg_maketoken_fail = 8026;
    public static final int ASrv_netcfg_verifytoken_fail = 8027;
    public static final int ASrv_parse_json_fail = 8028;
    public static final int Term_msg_send_peer_timeout = 20001;
    public static final int Term_msg_calling_hangup = 20002;
    public static final int Term_msg_calling_send_timeout = 20003;
    public static final int Term_msg_calling_no_srv_addr = 20004;
    public static final int Term_msg_calling_handshake_timeout = 20005;
    public static final int Term_msg_calling_token_error = 20006;
    public static final int Term_msg_calling_all_chn_busy = 20007;
    public static final int Term_msg_calling_timeout_disconnect = 20008;
    public static final int Term_msg_calling_no_find_dst_id = 20009;
    public static final int Term_msg_gdm_handle_processing = 20100;
    public static final int Term_msg_gdm_handle_leaf_path_error = 20101;
    public static final int Term_msg_gdm_handle_parse_json_fail = 20102;
    public static final int Term_msg_gdm_handle_fail = 20103;
    public static final int Term_msg_gdm_handle_no_cb_registered = 20104;
    public static final int ERROR_MESSAGE_ID = 22000;
    public static final int ERROR_TIMEOUT = 22001;
    public static final int ERROR_RESULT = 22002;
    public static final int TERM_MSG_USER_CMD_INTERNAL_ERROR = 20140;
    public static final int TERM_MSG_USER_CMD_TYPE_ERROR = 20141;
    public static final int TERM_MSG_USER_CMD_VERSION_ERROR = 20142;
    public static final int TERM_MSG_USER_CMD_PARAM_ERROR = 20143;

    @BeforeClass
    public void setUp() {
        long AccessId = Long.parseLong(getApplicationContext().getString(R.string.AccessId));
        String AccessToken = getApplicationContext().getString(R.string.AccessToken);

        IoTVideoSdk.init(getApplicationContext(),null);
        IoTVideoSdk.register(AccessId, AccessToken);
    }

    @Test
    public void test_ERROR_NONE() {
        Assert.assertEquals(IoTVideoError.ERROR_NONE, ERROR_NONE);
    }

    @Test
    public void test_AC_centerInner_find_login_user_err() {
        Assert.assertEquals(IoTVideoError.AC_centerInner_find_login_user_err, AC_centerInner_find_login_user_err);
    }

    @AfterClass
    public void tearDown() {
        IoTVideoSdk.unregister();
    }
}