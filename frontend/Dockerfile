# Build stage
FROM node:21-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json .
COPY package-lock.json .
RUN npm install
COPY . .
RUN npm run build

# Final stage
FROM node:21-alpine
WORKDIR /app
COPY --from=build /app/dist /app/dist
CMD ["npm", "run", "dev"]