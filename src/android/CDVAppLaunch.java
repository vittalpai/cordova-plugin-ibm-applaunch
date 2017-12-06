/*
    Copyright 2015 IBM Corp.
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.ibm.mobile.applaunch.android.cdvplugin;

import com.ibm.mobile.applaunch.android.api.AppLaunch;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.*;

public class CDVAppLaunch extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        if("initialize".equals(action)) {
            String appRegion = args.getString(0);
            String appGUID = args.getString(1);
            String clientSecret = args.getString(2);
            this.initialize(callbackContext, appRegion, appGUID,clientSecret);
            return true;
        }
        return false;
    }

    /**
     * Initialize the AppLaunch SDK with appRegion, appGUID and ClientSecret
     * @param callbackContext Javascript callback
     * @param appRegion - AppLaunch Service app region
     * @param appGUID - AppLaunch Service app GUID
     * @param clientSecret - AppLaunch Service clientSecret
     */
    private void initialize(final CallbackContext callbackContext, final String appRegion, final String appGUID, final String clientSecret) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                AppLaunch.getInstance().initApp(cordova.getActivity().getApplication() , appRegion, appGUID, clientSecret);
                callbackContext.success();
            }
        });
    }

}
