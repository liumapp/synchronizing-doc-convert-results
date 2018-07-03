/**
 * @file doc-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<template>
  <div>
    <br>
    <Row>
      <Col span="8" offset="8">
        <Upload
          multiple
          type="drag"
          :action="uploadUrl"
          :before-upload="handleFileToBase64"
          v-bind:default-file-list="readyUploadFile">
          <div style="padding: 20px 0">
            <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
            <p>Click or drag files here to upload</p>
          </div>
        </Upload>
      </Col>
    </Row>
    <Row>
      <Col span="2" offset="14">
        <Button type="primary" @click="submitPic">提交</Button>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util'
export default {
  name: 'doc-upload',
  data () {
    return {
      uploadUrl: util.ajaxUrl + "/upload/base64",
      file: {},
      readyUploadFile: []
    }
  },
  methods: {
    handleFileToBase64 (file) {
      let reader = new FileReader();
      let _vue = this;
      reader.readAsDataURL(file);
      reader.onload = function () {
        _vue.file = reader.result;
        _vue.readyUploadFile.push({name: file.name});
      }
      return false;
    },
    submitPic () {
      console.log(this.readyUploadFile)

    }
  }
}
</script>
