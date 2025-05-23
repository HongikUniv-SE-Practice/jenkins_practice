pipeline {
    agent any

    environment {
        JUNIT_JAR_URL = 'https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.1/junit-platform-console-standalone-1.7.1.jar'
        JUNIT_JAR_PATH = 'lib/junit.jar'
        CLASS_DIR = 'classes'
        REPORT_DIR = 'test-reports'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare') {
            steps {
                sh '''
                    mkdir -p $CLASS_DIR
                    mkdir -p $REPORT_DIR
                    mkdir -p lib
                    echo "[+] Downloading JUnit JARs..."
            		curl -L -o lib/junit-platform-console-standalone.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.7.1/junit-platform-console-standalone-1.7.1.jar
            		curl -L -o lib/junit-jupiter-api.jar https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.7.1/junit-jupiter-api-5.7.1.jar
            		curl -L -o lib/apiguardian-api.jar https://repo1.maven.org/maven2/org/apiguardian/apiguardian-api/1.1.0/apiguardian-api-1.1.0.jar
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                    echo "[+] Compiling source files..."                    
                    find src -name "*.java" > sources.txt
                    javac -encoding UTF-8 -d $CLASS_DIR -cp "lib/*" @sources.txt
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    echo "[+] Running tests with JUnit..."
                    java -jar $JUNIT_JAR_PATH \
                         --class-path $CLASS_DIR \
                         --scan-class-path \
                         --details=tree \
                         --details-theme=ascii \
                         --reports-dir $REPORT_DIR \
                         --config=junit.platform.output.capture.stdout=true \
                         --config=junit.platform.reporting.open.xml.enabled=true \
                         > $REPORT_DIR/test-output.txt
                '''
            }
        }
    }

    post {
        always {
            echo "[*] Archiving test results..."
            junit "$REPORT_DIR/**/*.xml"
            archiveArtifacts artifacts: "$REPORT_DIR/**/*", allowEmptyArchive: true
        }

        failure {
            echo "Build or test failed!"
        }
        
        // 빌드 성공 시 txt 파일 저장
		success {
        echo "Build and test succeeded!"
        sh '''
            echo "Build Success" > $REPORT_DIR/build-result.txt
            echo "Job: $JOB_NAME" >> $REPORT_DIR/build-result.txt
            echo "Build: #$BUILD_NUMBER" >> $REPORT_DIR/build-result.txt
            echo "URL: $BUILD_URL" >> $REPORT_DIR/build-result.txt
        '''
	    }
		
    }
}