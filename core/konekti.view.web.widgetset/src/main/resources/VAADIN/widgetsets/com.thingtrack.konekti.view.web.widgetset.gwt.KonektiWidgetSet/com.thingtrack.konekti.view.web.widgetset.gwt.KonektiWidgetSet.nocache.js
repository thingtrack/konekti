function com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet(){
  var $wnd_0 = window, $doc_0 = document, $stats = $wnd_0.__gwtStatsEvent?function(a){
    return $wnd_0.__gwtStatsEvent(a);
  }
  :null, $sessionId_0 = $wnd_0.__gwtStatsSessionId?$wnd_0.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = '', metaProps = {}, values = [], providers = [], answers = [], softPermutationId = 0, onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'begin'});
  if (!$wnd_0.__gwt_stylesLoaded) {
    $wnd_0.__gwt_stylesLoaded = {};
  }
  if (!$wnd_0.__gwt_scriptsLoaded) {
    $wnd_0.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd_0.location.search;
      return (query.indexOf('gwt.codesvr=') != -1 || (query.indexOf('gwt.hosted=') != -1 || $wnd_0.external && $wnd_0.external.gwtOnLoad)) && query.indexOf('gwt.hybrid') == -1;
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc_0.getElementById('com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet');
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name_0){
          return computePropValue(name_0);
        }
        ;
      }
      com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, 'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', base, softPermutationId);
      $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'moduleStartup', millis:(new Date).getTime(), type:'end'});
    }
  }

  function computeScriptBase(){
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf('#');
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf('?');
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf('/', Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):'';
    }

    function ensureAbsoluteUrl(url){
      if (url.match(/^\w+:\/\//)) {
      }
       else {
        var img = $doc_0.createElement('img');
        img.src = url + 'clear.cache.gif';
        url = getDirectoryOfFile(img.src);
      }
      return url;
    }

    function tryMetaTag(){
      var metaVal = __gwt_getMetaProperty('baseUrl');
      if (metaVal != null) {
        return metaVal;
      }
      return '';
    }

    function tryNocacheJsTag(){
      var scriptTags = $doc_0.getElementsByTagName('script');
      for (var i = 0; i < scriptTags.length; ++i) {
        if (scriptTags[i].src.indexOf('com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet.nocache.js') != -1) {
          return getDirectoryOfFile(scriptTags[i].src);
        }
      }
      return '';
    }

    function tryMarkerScript(){
      var thisScript;
      if (typeof isBodyLoaded == 'undefined' || !isBodyLoaded()) {
        var markerId = '__gwt_marker_com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet';
        var markerScript;
        $doc_0.write('<script id="' + markerId + '"><\/script>');
        markerScript = $doc_0.getElementById(markerId);
        thisScript = markerScript && markerScript.previousSibling;
        while (thisScript && thisScript.tagName != 'SCRIPT') {
          thisScript = thisScript.previousSibling;
        }
        if (markerScript) {
          markerScript.parentNode.removeChild(markerScript);
        }
        if (thisScript && thisScript.src) {
          return getDirectoryOfFile(thisScript.src);
        }
      }
      return '';
    }

    function tryBaseTag(){
      var baseElements = $doc_0.getElementsByTagName('base');
      if (baseElements.length > 0) {
        return baseElements[baseElements.length - 1].href;
      }
      return '';
    }

    var tempBase = tryMetaTag();
    if (tempBase == '') {
      tempBase = tryNocacheJsTag();
    }
    if (tempBase == '') {
      tempBase = tryMarkerScript();
    }
    if (tempBase == '') {
      tempBase = tryBaseTag();
    }
    if (tempBase == '') {
      tempBase = getDirectoryOfFile($doc_0.location.href);
    }
    tempBase = ensureAbsoluteUrl(tempBase);
    base = tempBase;
    return tempBase;
  }

  function processMetas(){
    var metas = document.getElementsByTagName('meta');
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name_0 = meta.getAttribute('name'), content_0;
      if (name_0) {
        name_0 = name_0.replace('com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet::', '');
        if (name_0.indexOf('::') >= 0) {
          continue;
        }
        if (name_0 == 'gwt:property') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            var value, eq = content_0.indexOf('=');
            if (eq >= 0) {
              name_0 = content_0.substring(0, eq);
              value = content_0.substring(eq + 1);
            }
             else {
              name_0 = content_0;
              value = '';
            }
            metaProps[name_0] = value;
          }
        }
         else if (name_0 == 'gwt:onPropertyErrorFn') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            try {
              propertyErrorFunc = eval(content_0);
            }
             catch (e) {
              alert('Bad handler "' + content_0 + '" for "gwt:onPropertyErrorFn"');
            }
          }
        }
         else if (name_0 == 'gwt:onLoadErrorFn') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            try {
              onLoadErrorFunc = eval(content_0);
            }
             catch (e) {
              alert('Bad handler "' + content_0 + '" for "gwt:onLoadErrorFn"');
            }
          }
        }
      }
    }
  }

  function __gwt_getMetaProperty(name_0){
    var value = metaProps[name_0];
    return value == null?null:value;
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc_0.createElement('iframe');
      iframe.src = "javascript:''";
      iframe.id = 'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet';
      iframe.style.cssText = 'position:absolute;width:0;height:0;border:none';
      iframe.tabIndex = -1;
      $doc_0.body.appendChild(iframe);
      $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'moduleStartup', millis:(new Date).getTime(), type:'moduleRequested'});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers['user.agent'] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (function(){
      return ua.indexOf('opera') != -1;
    }
    ())
      return 'opera';
    if (function(){
      return ua.indexOf('webkit') != -1;
    }
    ())
      return 'safari';
    if (function(){
      return ua.indexOf('msie') != -1 && $doc_0.documentMode >= 9;
    }
    ())
      return 'ie9';
    if (function(){
      return ua.indexOf('msie') != -1 && $doc_0.documentMode >= 8;
    }
    ())
      return 'ie8';
    if (function(){
      var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3)
        return makeVersion(result) >= 6000;
    }
    ())
      return 'ie6';
    if (function(){
      return ua.indexOf('gecko') != -1;
    }
    ())
      return 'gecko1_8';
    return 'unknown';
  }
  ;
  values['user.agent'] = {gecko1_8:0, ie6:1, ie8:2, ie9:3, opera:4, safari:5};
  com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'loadExternalRefs', millis:(new Date).getTime(), type:'end'});
    maybeStartModule();
  }
  ;
  processMetas();
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd_0.external && ($wnd_0.external.initModule && $wnd_0.external.initModule('com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet'))) {
      $wnd_0.location.reload();
      return;
    }
    initialHtml = 'hosted.html?com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet';
    strongName = '';
  }
  $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'selectingPermutation'});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers(['ie6'], '038AA892B48630A45746CAB4A892EF56');
      unflattenKeylistIntoAnswers(['ie8'], '1D7206703F690013EB202D5F4309E5E5');
      unflattenKeylistIntoAnswers(['opera'], '9BF77535B4B4F04F3BE05235278DAB7C');
      unflattenKeylistIntoAnswers(['ie9'], 'A17FC95D756C42B97384495D94A45436');
      unflattenKeylistIntoAnswers(['safari'], 'B321F5F192CB71CD27C72DE861EDFB24');
      unflattenKeylistIntoAnswers(['gecko1_8'], 'D2CAB89C4B20B4FD930E72D3E243EA52');
      strongName = answers[computePropValue('user.agent')];
      var idx = strongName.indexOf(':');
      if (idx != -1) {
        softPermutationId = Number(strongName.substring(idx + 1));
        strongName = strongName.substring(0, idx);
      }
      initialHtml = strongName + '.cache.html';
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded['konekti/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['konekti/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'konekti/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['animator/animator.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['animator/animator.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'animator/animator.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['popupbutton/popupbutton.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['popupbutton/popupbutton.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'popupbutton/popupbutton.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['splitbutton/splitbutton.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['splitbutton/splitbutton.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'splitbutton/splitbutton.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['qrcode/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['qrcode/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'qrcode/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['buttongroup-widget/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['buttongroup-widget/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'buttongroup-widget/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['ikaruswidgets/styles.css ']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['ikaruswidgets/styles.css '] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'ikaruswidgets/styles.css ');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['switch/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['switch/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'switch/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['com_vaadin_addon_calendar/calendar.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['com_vaadin_addon_calendar/calendar.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'com_vaadin_addon_calendar/calendar.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['colorpicker/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['colorpicker/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'colorpicker/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['stylecalendar.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['stylecalendar.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'stylecalendar.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['stylecalendarfield.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['stylecalendarfield.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'stylecalendarfield.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['fi_jasoft_dragdroplayouts/dragdroplayouts.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['fi_jasoft_dragdroplayouts/dragdroplayouts.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'fi_jasoft_dragdroplayouts/dragdroplayouts.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['filtertable/filtertable.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['filtertable/filtertable.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'filtertable/filtertable.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['PortalLayout/portallayout.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['PortalLayout/portallayout.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'PortalLayout/portallayout.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded['eventtimeline-widget/styles.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['eventtimeline-widget/styles.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'eventtimeline-widget/styles.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc_0.removeEventListener) {
        $doc_0.removeEventListener('DOMContentLoaded', onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc_0.addEventListener) {
    $doc_0.addEventListener('DOMContentLoaded', function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc_0.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'end'});
  $stats && $stats({moduleName:'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'loadExternalRefs', millis:(new Date).getTime(), type:'begin'});
  if (!__gwt_scriptsLoaded['http://maps.google.com/maps/api/js?v=3.2&sensor=false']) {
    __gwt_scriptsLoaded['http://maps.google.com/maps/api/js?v=3.2&sensor=false'] = true;
    document.write('<script language="javascript" src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"><\/script>');
  }
  if (!__gwt_scriptsLoaded['http://code.jquery.com/jquery-1.8.2.min.js']) {
    __gwt_scriptsLoaded['http://code.jquery.com/jquery-1.8.2.min.js'] = true;
    document.write('<script language="javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"><\/script>');
  }
  if (!__gwt_scriptsLoaded['http://code.highcharts.com/highcharts.js']) {
    __gwt_scriptsLoaded['http://code.highcharts.com/highcharts.js'] = true;
    document.write('<script language="javascript" src="http://code.highcharts.com/highcharts.js"><\/script>');
  }
  if (!__gwt_scriptsLoaded['http://code.highcharts.com/modules/exporting.js']) {
    __gwt_scriptsLoaded['http://code.highcharts.com/modules/exporting.js'] = true;
    document.write('<script language="javascript" src="http://code.highcharts.com/modules/exporting.js"><\/script>');
  }
  if (!__gwt_scriptsLoaded['helpers.js']) {
    __gwt_scriptsLoaded['helpers.js'] = true;
    document.write('<script language="javascript" src="' + base + 'helpers.js"><\/script>');
  }
  if (!__gwt_scriptsLoaded['http://openlayers.org/api/2.12/OpenLayers.js']) {
    __gwt_scriptsLoaded['http://openlayers.org/api/2.12/OpenLayers.js'] = true;
    document.write('<script language="javascript" src="http://openlayers.org/api/2.12/OpenLayers.js"><\/script>');
  }
  if (!__gwt_scriptsLoaded['overlays/overlays.js']) {
    __gwt_scriptsLoaded['overlays/overlays.js'] = true;
    document.write('<script language="javascript" src="' + base + 'overlays/overlays.js"><\/script>');
  }
  $doc_0.write('<script defer="defer">com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet.onInjectionDone(\'com.thingtrack.konekti.view.web.widgetset.gwt.KonektiWidgetSet\')<\/script>');
}

com_thingtrack_konekti_view_web_widgetset_gwt_KonektiWidgetSet();
