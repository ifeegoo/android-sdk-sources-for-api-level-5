package android.telephony;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.CellLocation;
import android.util.Log;

import com.android.internal.telephony.IPhoneStateListener;

/**
 * A listener class for monitoring changes in specific telephony states
 * on the device, including service state, signal strength, message
 * waiting indicator (voicemail), and others.
 * <p>
 * Override the methods for the state that you wish to receive updates for, and
 * pass your PhoneStateListener object, along with bitwise-or of the LISTEN_
 * flags to {@link TelephonyManager#listen TelephonyManager.listen()}.
 * <p>
 * Note that access to some telephony information is
 * permission-protected. Your application won't receive updates for protected
 * information unless it has the appropriate permissions declared in
 * its manifest file. Where permissions apply, they are noted in the
 * appropriate LISTEN_ flags.
 */
public class PhoneStateListener {

    /**
     * Stop listening for updates.
     */
    public static final int LISTEN_NONE = 0;

    /**
     *  Listen for changes to the network service state (cellular).
     *
     *  @see #onServiceStateChanged
     *  @see ServiceState
     */
    public static final int LISTEN_SERVICE_STATE                            = 0x00000001;

    /**
     * Listen for changes to the network signal strength (cellular).
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE}
     * <p>
     *
     * @see #onSignalStrengthChanged
     *
     * TODO: @deprecated to be deprecated by LISTEN_SIGNAL_STRENGTHS, @see #onSignalStrengthsChanged
     */
    public static final int LISTEN_SIGNAL_STRENGTH                          = 0x00000002;

    /**
     * Listen for changes to the message-waiting indicator.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE}
     * <p>
     * Example: The status bar uses this to determine when to display the
     * voicemail icon.
     *
     * @see #onMessageWaitingIndicatorChanged
     */
    public static final int LISTEN_MESSAGE_WAITING_INDICATOR                = 0x00000004;

    /**
     * Listen for changes to the call-forwarding indicator.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE}
     * @see #onCallForwardingIndicatorChanged
     */
    public static final int LISTEN_CALL_FORWARDING_INDICATOR                = 0x00000008;

    /**
     * Listen for changes to the device's cell location. Note that
     * this will result in frequent callbacks to the listener.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#ACCESS_COARSE_LOCATION
     * ACCESS_COARSE_LOCATION}
     * <p>
     * If you need regular location updates but want more control over
     * the update interval or location precision, you can set up a listener
     * through the {@link android.location.LocationManager location manager}
     * instead.
     *
     * @see #onCellLocationChanged
     */
    public static final int LISTEN_CELL_LOCATION                            = 0x00000010;

    /**
     * Listen for changes to the device call state.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE}
     * @see #onCallStateChanged
     */
    public static final int LISTEN_CALL_STATE                               = 0x00000020;

    /**
     * Listen for changes to the data connection state (cellular).
     *
     * @see #onDataConnectionStateChanged
     */
    public static final int LISTEN_DATA_CONNECTION_STATE                    = 0x00000040;

    /**
     * Listen for changes to the direction of data traffic on the data
     * connection (cellular).
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE}
     * Example: The status bar uses this to display the appropriate
     * data-traffic icon.
     *
     * @see #onDataActivity
     */
    public static final int LISTEN_DATA_ACTIVITY                            = 0x00000080;

    /**
     * Listen for changes to the network signal strengths (cellular).
     * <p>
     * Example: The status bar uses this to control the signal-strength
     * icon.
     *
     * @see #onSignalStrengthsChanged
     *
     * @hide
     */
    public static final int LISTEN_SIGNAL_STRENGTHS                         = 0x00000100;

    public PhoneStateListener() {
    }

    /**
     * Callback invoked when device service state changes.
     *
     * @see ServiceState#STATE_EMERGENCY_ONLY
     * @see ServiceState#STATE_IN_SERVICE
     * @see ServiceState#STATE_OUT_OF_SERVICE
     * @see ServiceState#STATE_POWER_OFF
     */
    public void onServiceStateChanged(ServiceState serviceState) {
        // default implementation empty
    }

    /**
     * Callback invoked when network signal strength changes.
     *
     * @see ServiceState#STATE_EMERGENCY_ONLY
     * @see ServiceState#STATE_IN_SERVICE
     * @see ServiceState#STATE_OUT_OF_SERVICE
     * @see ServiceState#STATE_POWER_OFF
     * @deprecated see #onSignalStrengthsChanged
     */
    @Deprecated
    public void onSignalStrengthChanged(int asu) {
        // default implementation empty
    }

    /**
     * Callback invoked when the message-waiting indicator changes.
     */
    public void onMessageWaitingIndicatorChanged(boolean mwi) {
        // default implementation empty
    }

    /**
     * Callback invoked when the call-forwarding indicator changes.
     */
    public void onCallForwardingIndicatorChanged(boolean cfi) {
        // default implementation empty
    }

    /**
     * Callback invoked when device cell location changes.
     */
    public void onCellLocationChanged(CellLocation location) {
        // default implementation empty
    }

    /**
     * Callback invoked when device call state changes.
     *
     * @see TelephonyManager#CALL_STATE_IDLE
     * @see TelephonyManager#CALL_STATE_RINGING
     * @see TelephonyManager#CALL_STATE_OFFHOOK
     */
    public void onCallStateChanged(int state, String incomingNumber) {
        // default implementation empty
    }

    /**
     * Callback invoked when connection state changes.
     *
     * @see TelephonyManager#DATA_DISCONNECTED
     * @see TelephonyManager#DATA_CONNECTING
     * @see TelephonyManager#DATA_CONNECTED
     * @see TelephonyManager#DATA_SUSPENDED
     */
    public void onDataConnectionStateChanged(int state) {
        // default implementation empty
    }

    /**
     * @hide
     * same as above, but with the network type.  Both called.
     */
    public void onDataConnectionStateChanged(int state, int networkType) {
    }

    /**
     * Callback invoked when data activity state changes.
     *
     * @see TelephonyManager#DATA_ACTIVITY_NONE
     * @see TelephonyManager#DATA_ACTIVITY_IN
     * @see TelephonyManager#DATA_ACTIVITY_OUT
     * @see TelephonyManager#DATA_ACTIVITY_INOUT
     * @see TelephonyManager#DATA_ACTIVITY_DORMANT
     */
    public void onDataActivity(int direction) {
        // default implementation empty
    }

    /**
     * Callback invoked when network signal strengths changes.
     *
     * @see ServiceState#STATE_EMERGENCY_ONLY
     * @see ServiceState#STATE_IN_SERVICE
     * @see ServiceState#STATE_OUT_OF_SERVICE
     * @see ServiceState#STATE_POWER_OFF
     *
     * @hide
     */
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        // default implementation empty
    }

    /**
     * The callback methods need to be called on the handler thread where
     * this object was created.  If the binder did that for us it'd be nice.
     */
    IPhoneStateListener callback = new IPhoneStateListener.Stub() {
        public void onServiceStateChanged(ServiceState serviceState) {
            Message.obtain(mHandler, LISTEN_SERVICE_STATE, 0, 0, serviceState).sendToTarget();
        }

        public void onSignalStrengthChanged(int asu) {
            Message.obtain(mHandler, LISTEN_SIGNAL_STRENGTH, asu, 0, null).sendToTarget();
        }

        public void onMessageWaitingIndicatorChanged(boolean mwi) {
            Message.obtain(mHandler, LISTEN_MESSAGE_WAITING_INDICATOR, mwi ? 1 : 0, 0, null)
                    .sendToTarget();
        }

        public void onCallForwardingIndicatorChanged(boolean cfi) {
            Message.obtain(mHandler, LISTEN_CALL_FORWARDING_INDICATOR, cfi ? 1 : 0, 0, null)
                    .sendToTarget();
        }

        public void onCellLocationChanged(Bundle bundle) {
            CellLocation location = CellLocation.newFromBundle(bundle);
            Message.obtain(mHandler, LISTEN_CELL_LOCATION, 0, 0, location).sendToTarget();
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            Message.obtain(mHandler, LISTEN_CALL_STATE, state, 0, incomingNumber).sendToTarget();
        }

        public void onDataConnectionStateChanged(int state, int networkType) {
            Message.obtain(mHandler, LISTEN_DATA_CONNECTION_STATE, state, networkType, null).
                    sendToTarget();
        }

        public void onDataActivity(int direction) {
            Message.obtain(mHandler, LISTEN_DATA_ACTIVITY, direction, 0, null).sendToTarget();
        }
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            Message.obtain(mHandler, LISTEN_SIGNAL_STRENGTHS, 0, 0, signalStrength).sendToTarget();
        }
    };

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //Log.d("TelephonyRegistry", "what=0x" + Integer.toHexString(msg.what) + " msg=" + msg);
            switch (msg.what) {
                case LISTEN_SERVICE_STATE:
                    PhoneStateListener.this.onServiceStateChanged((ServiceState)msg.obj);
                    break;
                case LISTEN_SIGNAL_STRENGTH:
                    PhoneStateListener.this.onSignalStrengthChanged(msg.arg1);
                    break;
                case LISTEN_MESSAGE_WAITING_INDICATOR:
                    PhoneStateListener.this.onMessageWaitingIndicatorChanged(msg.arg1 != 0);
                    break;
                case LISTEN_CALL_FORWARDING_INDICATOR:
                    PhoneStateListener.this.onCallForwardingIndicatorChanged(msg.arg1 != 0);
                    break;
                case LISTEN_CELL_LOCATION:
                    PhoneStateListener.this.onCellLocationChanged((CellLocation)msg.obj);
                    break;
                case LISTEN_CALL_STATE:
                    PhoneStateListener.this.onCallStateChanged(msg.arg1, (String)msg.obj);
                    break;
                case LISTEN_DATA_CONNECTION_STATE:
                    PhoneStateListener.this.onDataConnectionStateChanged(msg.arg1, msg.arg2);
                    PhoneStateListener.this.onDataConnectionStateChanged(msg.arg1);
                    break;
                case LISTEN_DATA_ACTIVITY:
                    PhoneStateListener.this.onDataActivity(msg.arg1);
                    break;
                case LISTEN_SIGNAL_STRENGTHS:
                    PhoneStateListener.this.onSignalStrengthsChanged((SignalStrength)msg.obj);
                    break;
            }
        }
    };
}