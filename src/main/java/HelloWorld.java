import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class HelloWorld implements SparkApplication {
	public static void main(String[] args) {
		new HelloWorld().init();
	}

	public static String hostname;

	@Override
	public void init() {
		String os = System.getProperty("os.name");
		try {
			if (os.startsWith("Windows")) {
				hostname = System.getenv("COMPUTERNAME").trim();
			} else {
				hostname = System.getenv("HOSTNAME").trim();
			}
		}
		catch (Exception e){
			hostname = "unknown";
		}
		get("/", (req, res) -> {
			res.type("application/json");
			return "{\"response\":\"Spark on " + hostname + "\"}";
		});
		get("/hello", (req, res) -> {
			res.type("application/json");
			return "{\"response\":\"Hello World\"}";
		});
		get("/hello/:name", (req, res) -> {
			res.type("application/json");
			return "{\"response\":\"Hello " + req.params(":name") + "\"}";
		});
	}
}
