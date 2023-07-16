docker run -p 9000:9000 -p 9090:9090 \
     --net=host \
     --name minio \
     -d --restart=always \
     -e "MINIO_ACCESS_KEY=minioadmin" \
     -e "MINIO_SECRET_KEY=minioadmin" \
     -v /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-hack/hack-cloud/hack-minio/data:/data \
     -v /Volumes/ONETSSD/IdeaProjects/learning-anything/learning-hack/hack-cloud/hack-minio/config:/root/.minio \
     minio/minio:RELEASE.2023-01-20T02-05-44Z.hotfix.b9b60d73d server \
     /data --console-address ":9090" -address ":9000"