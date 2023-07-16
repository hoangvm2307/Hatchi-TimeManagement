<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p class="text">Total time spent: ${requestScope.hours} hrs ${requestScope.minutes} minutes</p>
<p class="text">Time distribution by Days of Week (in Minutes)</p>
<canvas id="hourChart" width="100%" ></canvas>
<p class="text">Time distribution by Tags (in Minutes)</p>
<canvas id="tagChart" width="100%" ></canvas>
<script>
    var ctx = document.getElementById('hourChart');
    var myChart = new Chart(ctx, ${requestScope.chartConfig});
    var tagCtx = document.getElementById('tagChart');
    var myTagChart = new Chart(tagCtx, ${requestScope.chartTagConfig});
</script>