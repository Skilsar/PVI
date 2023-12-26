import axios from "axios";

export class ApiManager {



  // Получение значения куки

  static async get(url: string, params?: any) {
    try {

      let { data, headers } = await axios.get(
        `${process.env.REACT_APP_API_URL}/api${url}`,
        {
          params,
          withCredentials: true,
        }
      );
      if (
        headers["x-session-id"] &&
        headers["x-session-id"] !== sessionStorage.getItem("sessionId")
      )
        sessionStorage.setItem("sessionId", headers["x-session-id"]);
      return data;
    } catch (error: any) {
      if (error.response)
        console.error(`Error ${error.response.status}`, error.response.data);
      else throw error;
    }
  }

  static async post(url: string, body?: any) {
    try {
      await axios.post(`${process.env.REACT_APP_API_URL}/api${url}`, body);
    } catch (error: any) {
      if (error.response)
        console.error(`Error ${error.response.status}`, error.response.data);
      else throw error;
    }
  }

  static async put(url: string, body?: any) {
    try {
      await axios.put(`${process.env.REACT_APP_API_URL}/api${url}`, body);
    } catch (error: any) {
      if (error.response)
        console.error(`Error ${error.response.status}`, error.response.data);
      else throw error;
    }
  }

  static async delete(url: string) {
    try {
      await axios.delete(`${process.env.REACT_APP_API_URL}/api${url}`);
    } catch (error: any) {
      if (error.response)
        console.error(`Error ${error.response.status}`, error.response.data);
      else throw error;
    }
  }
}