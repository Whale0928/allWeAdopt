// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';


// --------------- 정석 ---------------------------------------------------------------------
const cArr1 = [];
const mArr1 = [];

(function funding(){

  $.ajax({
    url : contextPath + "/admin/mainFunding",
    type: "POST",
    dataType: "JSON",
    success : function(cList){
      console.log(cList[0]);
      console.log(cList[0].count);
      
      for(let i = 0; i < 12; i++){
        if(i==6){
          cArr1[i] = cList[0].count;
          mArr1[i] = cList[0].month;
        
        }
        // else if(i==7){
        //   cArr1[i] = cList[1].count;
        //   mArr1[i] = cList[1].month;

        // }
         else {
          cArr1[i] = 0;
          mArr1[i] = 0;
        }
      }
      console.log(cList[0].count);
      
      // Bar Chart Example
      var ctx = document.getElementById("myBarChart");
      var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
          datasets: [{
            label: "Revenue",
            backgroundColor: "rgba(251,131,107,1)",
            borderColor: "rgba(251,131,107,1)",
            data: cArr1,
          }],
        },
        options: {
          scales: {
            xAxes: [{
              time: {
                unit: 'month'
              },
              gridLines: {
                display: false
              },
              ticks: {
                maxTicksLimit: 12
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                max: 60,
                maxTicksLimit: 5
              },
              gridLines: {
                display: true
              }
            }],
          },
          legend: {
            display: false
          }
        }
      });


    },

    error : function(){

      console.log('펀딩 그래프 오류');

    }

  });

})();


