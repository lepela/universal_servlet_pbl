<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Toast Container -->
	
<%-- broadcast 처리 구간 시작 --%>

    
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 1055">
  <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <strong class="me-auto">알림</strong>
      <small>방금</small>
      <button type="button" class="btn-close btn-sm" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      여기에 메시지가 표시됩니다.
    </div>
  </div>
</div>
    <script>
    	const toastEl = document.getElementById('liveToast');
        const toast = new bootstrap.Toast(toastEl);
        const ws = new WebSocket('ws://' + location.host + '${cp}/notify')
        ws.onmessage = function(e) {
        	$(toastEl).find(".toast-body").html(e.data);
        	toast.show();	
        }
        
        
    </script>
<%-- broadcast 처리 구간 끝 --%>	
    <footer>
        <p>copyright &copy;</p>
        <address>대륭포스트 2차</address>
    </footer>
    <div class="arrow-area">
        <a href="#header"><i class="fa-regular fa-circle-up"></i></a>
    </div>