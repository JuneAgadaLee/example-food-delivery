<template>

<v-data-table
    :headers="headers"
    :items="progressPage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ProgressPage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
        ],
        progressPage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/progresspages')

      this.progressPage = temp.data._embedded.progresspages;

    },
    methods: {
    }
  }
</script>

