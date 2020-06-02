// pages/do_order/do_order.js
var app = getApp();
var host = app.globalData.host;
var getNowDate = function() {
  // 获取当前日期
  var date = new Date();
  var seperator1 = "-";
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  var hours = date.getHours();
  if (month >= 1 && month <= 9) {
      month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
      strDate = "0" + strDate;
  }
  return year + seperator1 + month + seperator1 + strDate;
}

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:"",
    worker_list: [],
    index: -1,
    startDate: '',
    date: '',
    time: '09:01',
    massage_id: 0,
    timeArr: [],
    selectedTime: ""
  },
  
  
  bindDateChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    
    if(this.data.index < 0) {
      wx.showToast({
        title: '请先选择技师',
        icon:'none',
        duration: 2000
      });
      return;
    }

    var startTime = 9;
    console.log(getNowDate());
    
    if(getNowDate() == e.detail.value) {
      startTime = parseInt(new Date().getHours) + 1
    }
    var timeList = [];
    for(var i = startTime; i <= 21; ++i) {
      timeList.push({
        value: "" + i + ":00:00",
        name: "" + i + ":00:00",
        disabled: false
      });
    }
    this.setData({
      timeArr: timeList
    });
    
    var that = this;
    wx.request({
      url: host + '/api/massageOrder/listByWorkerIdAndOrderTime',
      data: {
        workerId: this.data.index,
        orderTime: e.detail.value
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log(res.data)
        var retData = res.data.data;
        var orderTimeArr = [];
        for(var ot  in retData) {
          orderTimeArr.push(retData[ot].orderTime);
        }
        console.log(orderTimeArr);
        

        var timeList = that.data.timeArr;
        console.log(timeList);
        
        for(var i in timeList) {
          if(orderTimeArr.indexOf(that.data.date + " " + timeList[i].value) > -1) {
            timeList[i].disabled = true;
          }
        }
        that.setData({
          timeArr: timeList
        });
      }
    });
    this.setData({
      date: e.detail.value
    })

    
  },
  bindPickerChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    });
    var that = this;
    wx.request({
      url: host + '/api/massageOrder/listByWorkerIdAndOrderTime',
      data: {
        workerId: e.detail.value,
        orderTime: this.data.date
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log(res.data)
        var retData = res.data.data;
        var orderTimeArr = [];
        for(var ot  in retData) {
          orderTimeArr.push(retData[ot].orderTime);
        }
        console.log(orderTimeArr);
        

        var timeList = that.data.timeArr;
        console.log(timeList);
        
        for(var i in timeList) {
          if(orderTimeArr.indexOf(that.data.date + " " + timeList[i].value) > -1) {
            timeList[i].disabled = true;
          }
        }
        that.setData({
          timeArr: timeList
        });
        
      }
    });
  }, 
  radioChange(e) {
    console.log(e.detail);

    if(this.data.index < 0) {
      wx.showToast({
        title: '请选择技师',
        icon:'none',
        duration: 2000
      });
      return;
    }
    if(!this.data.date) {
      wx.showToast({
        title: '请选择日期',
        icon:'none',
        duration: 2000
      });
      return;
    }

    this.setData({
      selectedTime: e.detail.value
    });
  },

  formSubmit(e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
    let user_id = wx.getStorageSync('user').id

    if (e.detail.value.worker_idx < 0) {
      wx.showToast({
        title: '请选择技师',
        icon:'none',
        duration: 2000
      });
      return;
    }

    let worker_id = this.data.worker_list[e.detail.value.worker_idx].id;
    let massage_id = this.data.massage_id;
    if(!worker_id || worker_id < 1 || !massage_id || massage_id < 1) {
      wx.showToast({
        title: '提交信息有误',
        icon:'none',
        duration: 2000
      });
      return ;
    }

    let date = this.data.date;
    let selectedTime = this.data.selectedTime;
    if (!date || date == "" || !selectedTime || selectedTime == "") {
      wx.showToast({
        title: '选择时间有误',
        icon:'none',
        duration: 2000
      });
      return ;
    }


    wx.request({
      url: host + '/api/massageOrder/addMassageOrder',
      method: "POST",
      data: {
        userId: user_id,
        workerId: worker_id,
        massageId: massage_id,
        orderTime: new Date(date + " " + selectedTime)
      },
      dataType:"json",
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success (res) {
        console.log(res.data)
        if(res.data.code == 1) {
          wx.showToast({
            title: '提交成功',
            icon:'success',
            duration: 2000
          });
      
          setTimeout(function(){
            wx.switchTab({
              url: '/pages/morder/morder',
            })
          },1000);
        } else {
          wx.showToast({
            title: '提交失败',
            icon:'none',
            duration: 2000
          });
        }
      }
    })

    
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(!wx.getStorageSync('user')) {
      wx.reLaunch({
        url: '/pages/login/login',
      });
    }
    let that = this;
    wx.request({
      url: host + '/api/worker/listWorker',
      data: {},
      header: {
        'content-type': 'application/json' // 默认值
      },
      success (res) {
        console.log(res.data)
        that.setData({
          worker_list:res.data.data
        });
      }
    });
    this.setData({
      massage_id: options.mid,
      name:options.name
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // 获取当前日期
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;

    // var timeList = [];
    // var startTime = parseInt(hours);
    // if(startTime < 9) {
    //   startTime = 9;
    // }
    // for(var i = startTime + 1; i <= 21; ++i) {
    //   timeList.push({
    //     value: "" + i + ":00:00",
    //     name: "" + i + ":00:00",
    //     disabled: false
    //   });
    // }

    this.setData({
      // timeArr: timeList,
      // date: currentdate,
      startDate: currentdate
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})