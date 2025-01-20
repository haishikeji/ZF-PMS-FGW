<template>
    <el-select v-model="valueTmp" filterable remote reserve-keyword placeholder="请输入用户姓名" :remote-method="remoteMethod" :loading="loading" clearable :disabled="disabled" @change="onchange" @clear="onClear">
        <el-option v-for="item in options" :key="item.userId" :label="item.dept == undefined  ?item.nickName :item.nickName+'-'+item.dept.deptName" :value="item.userId">         
        <!-- <el-option v-for="item in options" :key="item.userId" :value="item.userId">          -->
          
        </el-option>
    </el-select> 
</template> 
<script>
import { listUser } from "@/api/system/user"; 
  export default { 
    name: "userSelect", 
    props: {  
      disabled: { 
        type: Boolean, 
        default: false  
      },  
      value: {  
        type: String, 
        default: '' 
      },
      queryType: { 
        type: Number,
        default: 0
      }
    },
   
    data() {
      
      return {   
        valueTmp: null,  
        options: [],  
        loading: false ,
      }  
    },

    created(){
      // this.value = null
    },
   
    watch: {   
      value: {  

        handler(val) {  
          console.log("enter watch:"+val )
          this.valueTmp = val  
          this.$nextTick(()=>{   
            if (val) {  
              this.remoteMethod(null, val) 
            } else {  
              this.remoteMethod(null)  
            } 
          })  
        }  
      },  
      valueTmp(val) { 
        this.$emit('input', val) 
      }  
    },  
    mounted() {  
      this.remoteMethod()  
      this.valueTmp = this.value
    },  
    methods: {
      remoteMethod(query, id) { 
        if (query !== '') {
          this.loading = true;  
          let req = {
            nickName: query,
            pageNum: 1,
            pageSize: 50
   
          }
          listUser(req).then((rsp)=>{
            this.options = rsp.rows
            this.loading = false;
          })
        } else {
          this.options = [];
        }
   
      },
      onchange(val) {
        let row = this.options.find(item=>item.userId === val)  
        this.$emit('change', val, row) 
      },
      onClear() {
        this.$emit('input', null)
      }
    }
  }
  </script>